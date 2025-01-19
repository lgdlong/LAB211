package Model;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" + "code=" + code + ", name=" + name + ", email=" + email + ", phone=" + phone + '}';
    }
    
    public String toCSVString() {
        return String.format("%s,%s,%s,%s", code, name, email, phone);
    }
}
