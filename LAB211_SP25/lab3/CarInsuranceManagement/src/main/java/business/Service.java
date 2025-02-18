package business;

import entity.model.Car;
import utils.InputData;
import utils.Validation;

public class Service {
    public void addCar() {
        String licensePlate = InputData.inputLicensePlate("Enter license plate: ");
        String ownerName = InputData.inputOwnerName("Enter owner name: ");
        String ownerPhone = InputData.inputOwnerPhone("Enter owner phone: ");
        String brand = InputData.inputBrand("");
        Long price = InputData.inputPrice("");

        Car car = new Car();
    }
}
