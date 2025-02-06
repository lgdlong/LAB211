package Repository;

/**
 *
 * @author LGD
 * @param <T>
 */
public interface I_Repository<T> {
    boolean add(T item);
    void display();
}