package Model;

public class Registration {

    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private String moutainCode;
    private double fee;

    public Registration(String id, String name, String phoneNumber, String email, String moutainCode) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.moutainCode = moutainCode;
        setFee();
    }

    public Registration(String id, String name, String phoneNumber, String email, String moutainCode, double fee) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.moutainCode = moutainCode;
        this.fee = fee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMoutainCode() {
        return moutainCode;
    }

    public void setMoutainCode(String moutainCode) {
        this.moutainCode = moutainCode;
    }

    public double getFee() {
        return fee;
    }

    public void setFee() {
        String first2Digit = phoneNumber.substring(0, 2);

        if (first2Digit.equals("03") || first2Digit.equals("08")) {
            this.fee = 6000000 * 65 / 100;
        } else {
            this.fee = 6000000;
        }
    }

    public String toCSVString() {
        return String.format("%s, %s, %s, %s, %s, %f", id, name, phoneNumber, email, moutainCode, fee);
    }
    
    public String getCampusForId(String id) {
        return id.substring(0, 2);
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-20s | %-12s | %-20s | %-13s | %-7.2f", id, name, phoneNumber, email, moutainCode, fee);
    }

}
