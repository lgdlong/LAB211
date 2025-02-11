package Repository;

import Dao.RegistrationDao;
import Model.Registration;
import Utils.Color;
import java.util.ArrayList;
import java.util.List;

public class RegistrationRepo {
    private List<Registration> registrationList = null;
    private final RegistrationDao registrationDao = new RegistrationDao();

    public RegistrationRepo() {
        loadData();
    }
    
    public void loadData() {
        this.registrationList = registrationDao.getAll();
    }

    public List<Registration> getRegistrationList() {
        return registrationList;
    }
    
    public boolean add(Registration reg) {
        if (reg == null) {
            return false;
        }
        
        return this.registrationList.add(reg);
    }
    
    public boolean delete (String regisId) {
        return deleteById(regisId) != null;
    }

    public Registration deleteById (String regisId) {
        Registration reg = getRegistrationById(regisId);
        
        if (reg == null) {
            return null;
        }
        
        this.registrationList.remove(reg);
        return reg;
    }
    
    
    public Registration getRegistrationById(String id) {
        return registrationList.stream()
                        .filter(reg -> reg.getId().equals(id))
                        .findFirst()
                        .orElse(null);
    }
    
    public boolean isExistId(String id) {
        return getRegistrationById(id) != null;
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
}
