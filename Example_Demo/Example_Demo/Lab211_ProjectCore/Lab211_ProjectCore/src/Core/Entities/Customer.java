package Core.Entities;

import Utilities.DataValidation;

public class Customer extends User {

    private String address;

    //Constructors
    public Customer(String id, String name, String address) throws Exception {
        super(id, name);
        setId(id);
        this.name = name;
        this.address = address;
    }
    //Setter and Getter... 
    //Methods...   
    //--------------------------------------------------
    @Override
    public void setId(String value) throws Exception {
        if (!DataValidation.checkStringWithFormat(value.toUpperCase(), "C\\d{3}")) {
            throw new Exception("Id invalid. The correct format:Exxx, with x is digits");
        }
        this.id = value;
    }
    //--------------------------------------------------
    public String getAddress() {
        return address;
    }
    //--------------------------------------------------
    public void setAddress(String address) {
        this.address = address;
    }
    //--------------------------------------------------
    @Override
    public String toString() {
        return String.format("%s, %s, %s", getId(), getName(), address);
    }
}
