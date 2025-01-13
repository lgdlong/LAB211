package Repository;

import Dao.MountainDao;
import Model.Mountain;
import java.util.ArrayList;
import java.util.List;

public class MountainRepo {
    private List<Mountain> mountainList = null;

    public MountainRepo() {
        loadData();        
        System.out.println("Load mountain's repository successful.");
    }

    public List<Mountain> getMountainList() {
        return mountainList;
    }

    public void setMountainList(List<Mountain> mountainList) {
        this.mountainList = mountainList;
    }
    
    public void loadData() {
        this.mountainList = new ArrayList<>(MountainDao.getAll());
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
