package Repository;

import Dao.FeastDao;
import Model.Feast;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author LGD
 */
public class FeastRepository implements I_Repository<Feast>{
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

    @Override
    public boolean add(Feast item) {
        if (item == null) {
            return false;
        }
        return feastList.add(item);
    }

    @Override
    public void display() {
        if (feastList.isEmpty()) {
            System.out.println("There is no feast to display.");
        }
        feastList.forEach(System.out::println);
    }
    
    public Feast getFeastByCode(String code) {
        return feastList.stream()
                .filter(feast -> code.equalsIgnoreCase(feast.getCode()))
                .findFirst()
                .orElse(null);
    }
}
