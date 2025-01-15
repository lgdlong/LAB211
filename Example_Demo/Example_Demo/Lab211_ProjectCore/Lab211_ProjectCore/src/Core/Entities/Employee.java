package Core.Entities;

import Utilities.DataValidation;


public class Employee extends User {    
    private String email;
    //Constructors
    public Employee(String id, String name,String email) throws Exception {
        super(id, name);
        setId(id);
        setEmail(email);
        setName(name);
    }
    
    @Override
    public void setId(String value) throws Exception{
        if(!DataValidation.checkStringWithFormat(value.toUpperCase(),"E\\d{3}")){
            throw new Exception("Id is invalid. The correct format:Exxx, with x is digits");
        }
        this.id = value;
    }
    
    public String getEmail() {        
        return email;
    }

    public void setEmail(String email)  throws Exception {
        if(!DataValidation.checkStringWithFormat(email,"^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")){
            throw new Exception("Email is invalid.");
        }
        this.email = email;
    }  
    //Methods....     
     @Override    
    public String toString(){
        return String.format("%s, %s, %s",getId(),getName(),email);
    }
}
