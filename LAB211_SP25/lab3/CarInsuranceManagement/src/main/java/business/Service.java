package business;

import entity.model.Car;
import entity.model.Insurance;
import repository.CarRepository;
import repository.InsuranceRepository;
import utils.InputData;
import utils.Validation;

import java.time.LocalDate;
import java.util.*;

public class Service {
    private static final CarRepository carRepository = new CarRepository();
    private static final InsuranceRepository insuranceRepository = new InsuranceRepository();

    public void addCar() {
        String licensePlate = InputData.inputLicensePlate("Enter license plate: ");
        String ownerName = InputData.inputOwnerName("Enter owner name: ");
        String ownerPhone = InputData.inputOwnerPhone("Enter owner phone: ");
        String brand = InputData.inputBrand("Enter brand: ");
        Long price = InputData.inputPrice("Enter price: ");
        LocalDate registrationDate = InputData.inputDate("Enter registration date (yyyy-MM-dd): ");
        int seatCount = InputData.inputSeatCount("Enter seat count: ");

        Car car = new Car(licensePlate, ownerName, ownerPhone, brand, price, registrationDate, seatCount);

        if (carRepository.insert(car)) {
            System.out.println("Car added successfully.");
        } else {
            System.out.println("Failed to add car.");
        }
    }

    public void updateCarInfo() {
        String licensePlate = InputData.inputLicensePlate("Enter license plate: ");
        Car car = carRepository.getCarByLicensePlate(licensePlate);

        if (car == null) {
            System.out.println("Car not found.");
            return;
        }

        Car oldCar = car.clone(); // create a clone to update

        System.out.println("Current car information:");
        System.out.println(car);

        String ownerName = InputData.inputOwnerName("Enter new owner name: ");
        String ownerPhone = InputData.inputOwnerPhone("Enter new owner phone: ");
        String brand = InputData.inputBrand("Enter new brand: ");
        Long price = InputData.inputPrice("Enter new price: ");
        LocalDate registrationDate = InputData.inputDate("Enter new registration date (yyyy-MM-dd): ");
        int seatCount = InputData.inputSeatCount("Enter new seat count: ");

        // Check if blank, using old data instead of new data
        ownerName = ownerName.equals("blank") ? car.getOwnerName() : ownerName;
        ownerPhone = ownerPhone.equals("blank") ? car.getOwnerPhone() : ownerPhone;
        brand = brand.equals("blank") ? car.getBrand() : brand;
        price = price == -1L ? car.getPrice() : price;
        registrationDate = registrationDate == null ? car.getRegistrationDate() : registrationDate;
        seatCount = seatCount == -1 ? car.getSeatCount() : seatCount;


        car.setOwnerName(ownerName);
        car.setOwnerPhone(ownerPhone);
        car.setBrand(brand);
        car.setPrice(price);
        car.setRegistrationDate(registrationDate);
        car.setSeatCount(seatCount);

        if (carRepository.update(oldCar, car)) {
            System.out.println("Car information updated successfully.");
        } else {
            System.out.println("Failed to update car information.");
        }
    }

    public void deleteCar() {
        String licensePlate = InputData.inputLicensePlate("Enter license plate: ");
        Car car = carRepository.getCarByLicensePlate(licensePlate);

        if (car == null) {
            System.out.println("Car not found.");
            return;
        }

        // check if the car is insured -> not delete and go to main menu
        if (Validation.isInsured(insuranceRepository, licensePlate)) {
            System.out.println("This car is insured. Cannot delete.");
            return;
        }

        System.out.println("Confirm to delete the car!");
        String choice = InputData.inputYesNo("Do you want to delete this car? (Y/N): ");

        if (choice.equalsIgnoreCase("Y")) {
            if (carRepository.delete(car) != null) {
                System.out.println("Car deleted successfully.");
            } else {
                System.out.println("Failed to delete car.");
            }
        }
    }

    public void findCarByLicensePlate() {
        String licensePlate = InputData.inputLicensePlate("Enter license plate: ");
        Car car = carRepository.getCarByLicensePlate(licensePlate);

        if (car == null) {
            System.out.println("Car not found.");
        } else {
            System.out.println(car);
        }
    }

    public void addInsuranceStatement() {
        String id = InputData.inputId("Enter id: ");
        if (insuranceRepository.isExits(id)) {
            System.out.println("Id already exists.");
            return;
        }

        String licensePlate = InputData.inputLicensePlate("Enter license plate: ");
        Car car = carRepository.getCarByLicensePlate(licensePlate);

        LocalDate establishedDate;
        do {
           establishedDate = InputData.inputDate("Enter established date (yyyy-MM-dd): ");
        } while (car.getRegistrationDate().isAfter(establishedDate));


        // validate the period
        int period;
        do {
            period = InputData.inputInt("Enter period: ");
        } while (period != 12 && period != 24 && period != 36);

        // calculate the fee
        Long carPrice = car.getPrice();
        double fee;
        if (period == 12) {
            fee = carPrice * 0.25;
        } else if (period == 24) {
            fee = carPrice * 2 * 0.2;
        } else {
            fee = carPrice * 3 * 0.15;
        }

        String ownerName = InputData.inputOwnerName("Enter owner name: ");

        // create a new insurance statement
        Insurance insurance = new Insurance(id, licensePlate, establishedDate, period, fee, ownerName);

        if (insuranceRepository.insert(insurance)) {
            System.out.println("Insurance statement added successfully.");
        } else {
            System.out.println("Failed to add insurance statement.");
        }
    }

    public void showAllInsuranceStatements() {
        boolean isEmpty = true;

        System.out.println("Enter date with format yyyy-MM-dd");
        LocalDate from = InputData.inputDate("From: ");
        LocalDate to = InputData.inputDate("To: ");

        for (Insurance insurance : insuranceRepository.getInsuranceRepo()) {
            if (insurance.getEstablishedDate().isAfter(from) && insurance.getEstablishedDate().isBefore(to)) {
                System.out.println(insurance);
                if (isEmpty) isEmpty = false;
            }
        }

        if (isEmpty) {
            System.out.println("No insurance statement found.");
        }
    }


    // it still error
    public void showUninsuredCars() {
        boolean isEmpty = true;

        List<Car> uninsuredCars = new ArrayList<>();

        String[] CAR_LICENSE_PLATES = carRepository.getCarRepo().stream().map(Car::getLicensePlate).toArray(String[]::new);
        String[] INSURANCE_LICENSE_PLATES = insuranceRepository.getInsuranceRepo().stream().map(Insurance::getLicensePlate).toArray(String[]::new);

        String[] uninsuredLicensePlates = Arrays.stream(CAR_LICENSE_PLATES)
                .filter(licensePlate -> Arrays.stream(INSURANCE_LICENSE_PLATES).noneMatch(licensePlate::equalsIgnoreCase))
                .toArray(String[]::new);

        System.out.println(Arrays.asList(CAR_LICENSE_PLATES));
        System.out.println(Arrays.asList(INSURANCE_LICENSE_PLATES));
        System.out.println(Arrays.asList(uninsuredLicensePlates));

//        for (Car car : carRepository.getCarRepo()) {
//            if (Arrays.asList(uninsuredLicensePlates).contains(car.getLicensePlate())) {
//                uninsuredCars.add(car);
//                if (isEmpty) isEmpty = false;
//            }
//        }
//
//        if (isEmpty) {
//            System.out.println("No uninsured car found.");
//        }
//
//        uninsuredCars.sort(Comparator.comparing(Car::getPrice));
//        uninsuredCars.forEach(System.out::println);
    }

    public void showAllCars() {
        carRepository.getCarRepo().forEach(System.out::println);
    }

    public void saveToFile() {

    }

}
