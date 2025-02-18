package entity.model;

import java.time.LocalDate;

public class Insurance {
    private String id;
    private String licensePlate;
    private LocalDate establishedDate;
    private int period;
    private int fee;
    private String ownerName;

    public Insurance(String id, String licensePlate, LocalDate establishedDate, int period, int fee, String ownerName) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.establishedDate = establishedDate;
        this.period = period;
        this.fee = fee;
        this.ownerName = ownerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDate getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(LocalDate establishedDate) {
        this.establishedDate = establishedDate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
