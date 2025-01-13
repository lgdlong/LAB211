package fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import model.Employee;

public class EmployeeFileBinary implements IFileReadWrite<Employee> {

    private final String FILE_NAME = "src/fileio/Employee.bin";

    @Override
    public List<Employee> read() throws Exception {
        List<Employee> list = null;
        FileInputStream fIn = null;
        ObjectInputStream inObj = null;
        try {
            fIn = new FileInputStream(FILE_NAME);
            inObj = new ObjectInputStream(fIn);
            Object obj = inObj.readObject();
            if (obj != null) {
                list = (List<Employee>) obj;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (inObj != null) {
                inObj.close();
            }
            if (fIn != null) {
                fIn.close();
            }
        }
        return list;
    }

    @Override
    public boolean write(List<Employee> list) throws Exception {
        FileOutputStream fOut = null;
        ObjectOutputStream outObj = null;
        try {
            fOut = new FileOutputStream(FILE_NAME);
            outObj = new ObjectOutputStream(fOut);
            outObj.writeObject(list);
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (outObj != null) {
                outObj.close();
            }
            if (fOut != null) {
                fOut.close();
            }
        }
        return true;
    }
}
