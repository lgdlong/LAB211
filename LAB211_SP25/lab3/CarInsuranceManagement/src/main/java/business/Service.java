package business;

import entity.model.Car;
import utils.InputData;
import utils.Validation;

import java.time.LocalDate;

public class Service {
    public void addCar() {
        String licensePlate = InputData.inputLicensePlate("Enter license plate: ");
        String ownerName = InputData.inputOwnerName("Enter owner name: ");
        String ownerPhone = InputData.inputOwnerPhone("Enter owner phone: ");
        String brand = InputData.inputBrand("Enter brand: ");
        Long price = InputData.inputPrice("Enter price: ");
        LocalDate registrationDate = InputData.inputRegistrationDate("Enter registration date: ");
        int seatCount = InputData.inputSeatCount("Enter seat count: ");

        Car car = new Car(licensePlate, ownerName, ownerPhone, brand, price, registrationDate, registrationPlace, seatCount);
    }
}
