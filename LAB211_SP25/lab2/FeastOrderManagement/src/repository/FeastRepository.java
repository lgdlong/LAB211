package repository;

import dao.FeastDao;
import model.Feast;
import repository.itf.IFeastRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author LGD
 */
public class FeastRepository implements IFeastRepository {
    List<Feast> feastList = null;
    
    public FeastRepository() {
        loadData();
    }

    private void loadData() {
        this.feastList = Optional.ofNullable(new FeastDao().getAll())
                               .orElseGet(ArrayList::new);
    }

    public List<Feast> getFeastList() {
        return feastList;
    }
}
