package Repository;

import Dao.RegistrationDao;
import Model.Registration;
import java.util.ArrayList;
import java.util.List;

public class RegistrationRepo {
    private List<Registration> registrationList = null;

    public RegistrationRepo() {
        loadData();
        System.out.println("Load registration's repository successful.");
    }

    public List<Registration> getRegistrationList() {
        return registrationList;
    }

    public void setRegistrationList(List<Registration> registrationList) {
        this.registrationList = registrationList;
    }
    
    public boolean add(Registration reg) {
        if (reg == null) {
            System.out.println("Registration is null.");
            return false;
        }
        
        return this.registrationList.add(reg);
    }
    
    public boolean delete (String regisId) {
        Registration reg = getRegistrationById(regisId);
        
        if (reg == null) {
            System.out.println("ID not found. Reg is null.");
            return false;
        }
        
        return this.registrationList.remove(reg);
    }
    
    public void loadData() {
        this.registrationList = new ArrayList<>(new RegistrationDao().getAll());
    }
    
    private Registration getRegistrationById(String id) {
        for (Registration reg : registrationList) {
            if (reg.getId().equals(id)) {
                return reg;
            }
        }
        return null;
    }
    
    public List<String> getIdList() {
        if (registrationList.isEmpty()) {
            return null;
        }
        
        // work
        List<String> idList = new ArrayList<>();
        
        for (Registration reg : registrationList) {
            idList.add(reg.getId());
        }
        
        return idList;
    }
    
    public String getCampusForId(String id) {
        return id.substring(0, 2);
    }
}
