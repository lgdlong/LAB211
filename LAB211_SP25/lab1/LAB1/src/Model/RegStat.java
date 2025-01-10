package Model;
public class RegStat {
    private int mountainCode;
    private String mountainName;
    private int totalRegistration;
    private double totalFee;

    public RegStat(int mountainCode, String mountainName, int totalRegistration, double totalFee) {
        this.mountainCode = mountainCode;
        this.mountainName = mountainName;
        this.totalRegistration = totalRegistration;
        this.totalFee = totalFee;
    }

    public int getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(int mountainCode) {
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
        return String.format("%-20s | %-22d | %-10.2f", mountainName, totalRegistration, totalFee);
    }
}