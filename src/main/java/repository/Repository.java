package repository;

import java.util.List;

public interface Repository <T> {
    List<T> list();

    T byId(Long id);

    void save(T t);

    void delete(Long id);

    void update(T t);
}
