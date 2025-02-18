package config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class RegionConfig {
    private static Map<String, String> REGIONS;
    private static final String FILE_PATH = "src/main/resources/regions.txt";

    public RegionConfig() {
        REGIONS = new HashMap<>();
        loadData();
    }

    public void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String data = reader.readLine();

            if (data != null) {
                String[] region = data.split(";");
                for (String s : region) {
                    String[] temp = s.split(":");
                    REGIONS.put(temp[0], temp[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getREGIONS() {
        return REGIONS;
    }

    public String getRegion(String key) {
        return REGIONS.getOrDefault(key, "Unknown");
    }
}
