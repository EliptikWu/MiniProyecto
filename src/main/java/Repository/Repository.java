package Repository;

import java.util.List;

public interface Repository <T> {
    List<T> list();

    T byId(Long id);

    void delete(Long id);

    void update(T t);
}
