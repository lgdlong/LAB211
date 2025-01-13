/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.List;
import model.Employee;

/**
 *
 * @author nguye
 */
public class TextFileReadWriteString implements IFileReadWrite<String> {

    private final String FILE_NAME = "src/fileio/Employee.txt";

    @Override
    public List<String> read() throws Exception {
        List<String> list = null;
        File f;
        try {
            f = new File(FILE_NAME);
            list = Files.readAllLines(f.toPath());
        } catch (Exception ex) {
            throw ex;
        }
        return list;
    }

    @Override
    public boolean write(List<String> list) throws Exception {
        File f;
        FileOutputStream file;
        BufferedWriter myOutput;// create Buffer    
        try {
            f = new File(FILE_NAME);//open file

            String fullPath = f.getAbsolutePath(); //get Fullpath of file
            file = new FileOutputStream(fullPath);
            myOutput = new BufferedWriter(new OutputStreamWriter(file));
            // write line until the end of the file
            for (String s : list) {
                myOutput.write(s);
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
