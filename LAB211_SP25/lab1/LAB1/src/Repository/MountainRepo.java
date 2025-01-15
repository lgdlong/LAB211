package Repository;

import Dao.MountainDao;
import Model.Mountain;
import Utils.Color;
import java.util.ArrayList;
import java.util.List;

public class MountainRepo {
    private List<Mountain> mountainList = null;

    public MountainRepo() {
        loadData();        
        System.out.println(Color.ANSI_YELLOW + "Load mountain's repository successful." + Color.ANSI_RESET);
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
