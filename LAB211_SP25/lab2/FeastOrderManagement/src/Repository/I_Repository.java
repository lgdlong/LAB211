package Repository;

import java.util.List;

/**
 *
 * @author LGD
 */
public interface I_Repository<T> {
    boolean add(T item);
    void display();
}