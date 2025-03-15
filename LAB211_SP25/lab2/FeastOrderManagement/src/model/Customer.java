package model;

/**
 *
 * @author LGD
 */
public class Customer {
    private String code;
    private String name;
    private String email;
    private String phone;

    public Customer(String code, String name, String email, String phone) {
        this.code = code;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        String[] name = this.name.split(" ");
        return name[name.length - 1];
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return String.format("%-5s | %-30s | %-30s | %-10s", code, name, email, phone);
    }
}
