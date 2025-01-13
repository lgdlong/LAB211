/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Developer;
import model.Employee;
import model.TeamLeader;
import model.Tester;

/**
 *
 * @author nguye
 */
public class EmployeeFileText implements IFileReadWrite<Employee> {

    private final String FILE_NAME = "src/fileio/Employee.txt";

    @Override
    public List<Employee> read() throws Exception {
        List<Employee> list = new ArrayList<>();
        File f;
        FileInputStream file;
        BufferedReader myInput;// create Buffer    
        try {
            f = new File(FILE_NAME);//open file
            //orther read text file
            List<String> data = Files.readAllLines(f.toPath());
            for(String line: data){
//            
//            //orginal read text file    
//            String fullPath = f.getAbsolutePath(); //get Fullpath of file
//            file = new FileInputStream(fullPath);
//            myInput = new BufferedReader(new InputStreamReader(file));
//            // read line until the end of the file
//            String line;
//            while ((line = myInput.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                Employee emp = null;
                String[] split = line.trim().split("_");
                switch (split.length) {
                    case 5: // tester
                        String code = split[0].trim();
                        String name = split[1].trim();
                        double baseSal = Double.parseDouble(split[2].trim());
                        double bonus = Double.parseDouble(split[3].trim());
                        String type = split[4].trim();
                        emp = new Tester(code, name, baseSal, bonus, type);
                        list.add(emp);
                        break;
                    case 6: // deverloper
                        code = split[0].trim();
                        name = split[1].trim();
                        baseSal = Double.parseDouble(split[2].trim());
                        String team = split[3].trim();
                        String pl = split[4].trim();
                        pl = pl.substring(1, pl.length() - 1); //remove [ ]
                        List<String> programming = Arrays.asList(pl.split(","));
                        int exp = Integer.parseInt(split[5].trim());
                        emp = new Developer(code, name, baseSal, team,
                                programming, exp);
                        list.add(emp);
                        break;
                    case 7: // teamleader
                        code = split[0].trim();
                        name = split[1].trim();
                        baseSal = Double.parseDouble(split[2].trim());
                        team = split[3].trim();
                        pl = split[4].trim();
                        pl = pl.substring(1, pl.length() - 1); //remove [ ]
                        programming = Arrays.asList(pl.split(","));
                        exp = Integer.parseInt(split[5].trim());
                        bonus = Double.parseDouble(split[6].trim());
                        emp = new TeamLeader(code, name, baseSal, team,
                                programming, exp, bonus);
                        list.add(emp);
                        break;
                }
            }
//            myInput.close();
//            file.close();
        } catch (Exception ex) {
            throw ex;
        }
        return list;
    }

    @Override
    public boolean write(List<Employee> list) throws Exception {
        File f;
        FileOutputStream file;
        BufferedWriter myOutput;// create Buffer    
        try {
            f = new File(FILE_NAME);//open file

            String fullPath = f.getAbsolutePath(); //get Fullpath of file
            file = new FileOutputStream(fullPath);
            myOutput = new BufferedWriter(new OutputStreamWriter(file));
            // write line until the end of the file
            for (Employee emp : list) {
                myOutput.write(emp.toString());
                myOutput.newLine();
            }
            myOutput.close();
            file.close();
        } catch (Exception ex) {
            throw ex;
        }
        return true;
    }

}
