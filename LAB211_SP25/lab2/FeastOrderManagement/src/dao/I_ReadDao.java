package dao;

import java.util.List;

/**
 *
 * @author LGD
 * @param <T>
 */
public interface I_ReadDao<T> {
    List<T> getAll();
}
