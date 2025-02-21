package utils;

import business.Service;

public class Menu {
    public static void printMenu() {
        Service service = new Service();
        int choice;

        do {
            System.out.println("\n====== CAR MANAGEMENT SYSTEM ======");
            System.out.println("1. Add new car");
            System.out.println("2. Find a car by license plate");
            System.out.println("3. Update car information");
            System.out.println("4. Delete car");
            System.out.println("5. Add insurance statement");
            System.out.println("6. Show all insurance statements");
            System.out.println("7. Show uninsured cars");
            System.out.println("8. Show all cars");
            System.out.println("0. Exit");


            choice = InputData.inputChoice("Enter your choice: ", 0, 8);

            switch (choice) {
                case 1:
                    service.addCar();
                    break;
                case 2:
                    service.findCarByLicensePlate();
                    break;
                case 3:
                    service.updateCarInfo();
                    break;
                case 4:
                    service.deleteCar();
                case 5:
                    service.addInsuranceStatement();
                    break;
                case 6:
                    service.showAllInsuranceStatements();
                    break;
                case 7:
                    service.showUninsuredCars();
                case 8:
                    service.showAllCars();
                    break;
                case 0:
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
                    break;
            }
        } while (choice != 0);
    }
}
