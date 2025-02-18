import config.RegionConfig;

import java.util.Map;

public class Program {
    public static void main(String[] args) {
        RegionConfig regionConfig = new RegionConfig();
        System.out.println(regionConfig.getRegion("F0"));
    }
}
