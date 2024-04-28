package Service;

import Domain.Dtos.ClientDto;

import java.util.List;

public interface Service <T>{
        List<T> list();
        T byId(Long id);
        void save(T t);
        void delete(Long id);

        void update(T t);
}
