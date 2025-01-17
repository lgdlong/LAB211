package Model;
public class RegStat {
    private String mountainCode;
    private String mountainName;
    private int totalRegistration;
    private double totalFee;

    public RegStat(String mountainCode, String mountainName, int totalRegistration, double totalFee) {
        this.mountainCode = mountainCode;
        this.mountainName = mountainName;
        this.totalRegistration = totalRegistration;
        this.totalFee = totalFee;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public String getMountainName() {
        return mountainName;
    }

    public void setMountainName(String mountainName) {
        this.mountainName = mountainName;
    }

    public int getTotalRegistration() {
        return totalRegistration;
    }

    public void setTotalRegistration(int totalRegistration) {
        this.totalRegistration = totalRegistration;
    }
    
    public void increTotalRegistrationByOne() {
        this.totalRegistration = totalRegistration + 1;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public void increTotalFeeBy(double fee) {
        this.totalFee = this.totalFee + fee;
    }

    @Override
    public String toString() {
        return String.format("%-9s | %-22d | %-10.2f", mountainCode, totalRegistration, totalFee);
    }
}