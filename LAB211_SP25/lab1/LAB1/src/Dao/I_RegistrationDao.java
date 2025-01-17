package Dao;

import Model.Registration;
import Repository.RegistrationRepo;
import java.util.List;

public interface I_RegistrationDao {
    List<Registration> getAll();
    boolean save(RegistrationRepo regs);
}
