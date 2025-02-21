package repository;

import dao.sql.InsuranceDaoSql;
import entity.model.Insurance;

import java.util.List;
import java.util.ArrayList;

public class InsuranceRepository {
    List<Insurance> insuranceRepo;

    public InsuranceRepository() {
        this.insuranceRepo = new ArrayList<>();
        loadData();
    }

    public List<Insurance> getInsuranceRepo() {
        return insuranceRepo;
    }

    public boolean insert(Insurance insurance) {
        if (insurance == null || isExits(insurance)) {
            return false;
        }

        insuranceRepo.add(insurance); // push data into repository
        new InsuranceDaoSql().insert(insurance); // push data into database

        return true;
    }

    private boolean isExits(Insurance insurance) {
        for (Insurance i : insuranceRepo) {
            if (i.getId().equals(insurance.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean isExits(String id) {
        for (Insurance i : insuranceRepo) {
            if (i.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private void loadData() {
        insuranceRepo = new InsuranceDaoSql().getAll();
    }

}
