package Repository;

import Dao.FeastDao;
import Model.Feast;
import java.util.List;

/**
 *
 * @author LGD
 */
public class FeastRepository implements I_Repository<Feast>{
    List<Feast> feastList = null;
    
    public FeastRepository() {
        loadData();
        System.out.println("Load feasts sucessful.");
    }

    private void loadData() {
        FeastDao dao = new FeastDao();
        feastList = dao.getAll();
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
            System.out.println("There is no feast fo display.");
        }
        
        feastList.stream()
                 .forEach(feast -> System.out.println(feast));
    }
    
    public Feast getFeastByCode(String code) {
        return feastList.stream()
                .filter(feast -> code.equalsIgnoreCase(feast.getCode()))
                .findFirst()
                .orElse(null);
    }
}
