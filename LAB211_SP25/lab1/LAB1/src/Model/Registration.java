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

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + ", moutainCode=" + moutainCode + ", fee=" + fee + '}';
    }
           
}
