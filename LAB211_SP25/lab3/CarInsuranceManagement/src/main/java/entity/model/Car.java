package entity.model;

import java.time.LocalDate;

public class Car {
    private String licensePlate;
    private String ownerName;
    private String ownerPhone;
    private String brand;
    private Long price;
    private LocalDate registrationDate;
    private String registrationPlace;
    private int seatCount;

    // constructor to create new car
    public Car(String licensePlate, String ownerName, String ownerPhone, String brand, Long price, LocalDate registrationDate, int seatCount) {
        this.licensePlate = licensePlate;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
        this.brand = brand;
        this.price = price;
        this.registrationDate = registrationDate;
        this.registrationPlace = getRegistrationPlace();
        this.seatCount = seatCount;
    }
    // Constructor to get all data from the database
    public Car(String licensePlate, String ownerName, String ownerPhone, String brand, Long price, LocalDate registrationDate, String registrationPlace, int seatCount) {
        this.licensePlate = licensePlate;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
        this.brand = brand;
        this.price = price;
        this.registrationDate = registrationDate;
        this.registrationPlace = registrationPlace;
        this.seatCount = seatCount;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegistrationPlace() {
        licensePlate = licensePlate.substring(0, 2) + "-" + licensePlate.substring(2, 5) + "." + licensePlate.substring(5, 7);
        return registrationPlace;
    }

    public void setRegistrationPlace(String registrationPlace) {
        this.registrationPlace = registrationPlace;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }
}
