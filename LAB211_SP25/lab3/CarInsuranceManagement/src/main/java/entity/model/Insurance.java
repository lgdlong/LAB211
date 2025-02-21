package entity.model;

import java.time.LocalDate;

public class Insurance {
    private String id;
    private String licensePlate;
    private LocalDate establishedDate;
    private int period;
    private double fee;
    private String ownerName;

    public Insurance(String id, String licensePlate, LocalDate establishedDate, int period, double fee, String ownerName) {
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

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "id='" + id + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", establishedDate=" + establishedDate +
                ", period=" + period +
                ", fee=" + fee +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
