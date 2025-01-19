package Dao;

import java.util.List;

/**
 *
 * @author LGD
 * @param <T>
 */
public interface I_FullAccessDao<T> {
    boolean save(List<T> tList);
    List<T> getAll();
}
