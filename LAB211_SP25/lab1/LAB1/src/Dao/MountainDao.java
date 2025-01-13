package Dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

import Model.Mountain;

public class MountainDao {

    private static final String filePath = "src\\Resources\\MountainList.csv";

    public static List<Mountain> getAll() {
        List<Mountain> mountains = new ArrayList<>();

        String line = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // pass a first line

            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",\\s*"); //split data at each line by comma.

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

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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
        List<String> s = new ArrayList<>();

        // Van de: Neu String[] nhan vao bawng spit khong co du 4 phan tu thi sao
        int lengthOfRowNotEmpty = data.length;

        for (int i = 1; i <= 4; i++) {
            if (i <= lengthOfRowNotEmpty) {
                s.add(data[i - 1]);
            } else {
                s.add("NULL");
            }
        }

        int code = Integer.parseInt(s.getFirst());
        String name = s.get(1);
        String province = s.get(2);
        String description = s.getLast();

        return new Mountain(code, name, province, description);
    }
}
