package Model;

public class Mountain {
    private int id;
    private String mountainName;
    private String province;
    private String description;

    public Mountain(int id, String mountainName, String province, String description) {
        this.id = id;
        this.mountainName = mountainName;
        this.province = province;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMountainName() {
        return mountainName;
    }

    public void setMountainName(String mountainName) {
        this.mountainName = mountainName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%-4d | %-20s | %-10s | %-4s", id, mountainName, province, description);
    }
           
}
