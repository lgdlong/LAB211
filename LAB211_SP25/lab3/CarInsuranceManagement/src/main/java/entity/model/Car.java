package entity.model;

import config.RegionConfig;

import java.time.LocalDate;

public class Car implements Cloneable {
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
        RegionConfig regionConfig = new RegionConfig();
        return regionConfig.getRegion(licensePlate.substring(2, 3));
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

    @Override
    public String toString() {
        return "Car{" +
                "licensePlate='" + licensePlate + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerPhone='" + ownerPhone + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", registrationDate=" + registrationDate +
                ", registrationPlace='" + registrationPlace + '\'' +
                ", seatCount=" + seatCount +
                '}';
    }

    @Override
    public Car clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Car) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
