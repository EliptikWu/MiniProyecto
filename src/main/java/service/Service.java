package service;

import java.util.List;

public interface Service <T>{
        List<T> list();
        T byId(Long id);
        void update(T t);
        void delete(Long id);
}
