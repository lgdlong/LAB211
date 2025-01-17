package Repository;

import Dao.MountainDao;
import Model.Mountain;
import Utils.Color;
import java.util.ArrayList;
import java.util.List;

public class MountainRepo {
    private List<Mountain> mountainList = null;
    private MountainDao mountainDao = new MountainDao();

    public MountainRepo() {
        loadData();        
        System.out.println(Color.ANSI_YELLOW + "Load mountain's repository successful." + Color.ANSI_RESET);
    }

    public List<Mountain> getMountainList() {
        return mountainList;
    }
    
    public void loadData() {
        this.mountainList = mountainDao.getAll();
    }
    
    public Mountain getMoutainByCode(int id) {
        return mountainList.stream()
                        .filter(mountain -> mountain.getId() == id)
                        .findFirst()
                        .orElse(null);
    }
    
    public List<Integer> getCodeList() {
        if (mountainList.isEmpty()) {
            return null;
        }
        
        // work
        List<Integer> codeList = new ArrayList<>();
        
        for (Mountain mountain : mountainList) {
            codeList.add(mountain.getId());
        }
        
        return codeList;
    }
}
