package repository;

import dao.sql.CarDaoSql;
import entity.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {
    List<Car> carRepo;

    public List<Car> getCarRepo() {
        return carRepo;
    }

    public CarRepository() {
        this.carRepo = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        carRepo = new CarDaoSql().getAll();
    }

    // checking this method again
    public boolean insert(Car car) {
        if (car == null || isExits(car)) {
            return false;
        }

        carRepo.add(car); // push data into repository
        new CarDaoSql().insert(car); // push data into database

        return true;
    }

    public boolean update(Car oldCar, Car newCar) {
        if (oldCar == null || newCar == null) {
            return false;
        }

        for (Car car : carRepo) {
            if (car.getLicensePlate().equals(oldCar.getLicensePlate())) {
                car.setOwnerName(newCar.getOwnerName());
                car.setOwnerPhone(newCar.getOwnerPhone());
                car.setBrand(newCar.getBrand());
                car.setPrice(newCar.getPrice());
                car.setRegistrationDate(newCar.getRegistrationDate());
                car.setRegistrationPlace(newCar.getRegistrationPlace());
                car.setSeatCount(newCar.getSeatCount());
                return true;
            }
        }

        // sql
        new CarDaoSql().update(oldCar, newCar);
        return false;
    }

    public Car delete(Car car) {
        for (Car c : carRepo) {
            if (c.getLicensePlate().equals(car.getLicensePlate())) {
                carRepo.remove(c);
                return c;
            }
        }
        return null;
    }

    public Car getCarByLicensePlate(String licensePlate) {
        for (Car car : carRepo) {
            if (car.getLicensePlate().equals(licensePlate)) {
                return car;
            }
        }
        return null;
    }

    private boolean isExits(Car car) {
        for (Car c : carRepo) {
            if (c.getLicensePlate().equals(car.getLicensePlate())) {
                return true;
            }
        }
        return false;
    }
}