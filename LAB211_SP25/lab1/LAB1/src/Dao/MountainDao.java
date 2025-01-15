package Dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

import Model.Mountain;
import java.io.File;

public class MountainDao {

    private static final String FILE_PATH = "src\\Resources\\MountainList.csv";
    private static final File FILE = new File(FILE_PATH);

    public static List<Mountain> getAll() {
        List<Mountain> mountains = new ArrayList<>();

        if (!FILE.exists()) {
            System.out.println("File not found: " + FILE_PATH);
            return mountains;
        }
        
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            reader.readLine(); // pass a first line

            while ((line = reader.readLine()) != null) {
                String[] row = line.split(","); //split data at each line by comma.

                mountains.add(getMountainByLine(row)); // put each moutain in to list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mountains;
    }

    public static Mountain getMoutainByCode(int code) {
        Mountain m = null;
        String line = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            reader.readLine(); // pass a first line

            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",\\s*"); //split data at each line by comma.

                if ((m = getMountainByLine(row)).getId() == code) {
                    return m;
                } // put each moutain in to list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return m;
    }

    private static Mountain getMountainByLine(String[] data) {
        if (data.length == 0) {
            return null;
        }

        String[] filledData = new String[4];
        for (int i = 0; i < 4; i++) {
            filledData[i] = (i < data.length && !data[i].isBlank()) ? data[i] : "NULL";
        }

        int code = Integer.parseInt(filledData[0].trim());
        String name = filledData[1].trim();
        String province = filledData[2].trim();
        String description = filledData[3].trim();

        return new Mountain(code, name, province, description);
    }
}
