package service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mapping.dtos.UserDto;
import repository.Repository;
import service.Service;

import java.util.List;

/**
 *
 * @author <a href=""
 */
@ApplicationScoped
public class UserServiceImpl implements Service<UserDto> {
    @Inject
    @Named("User")
    private Repository<UserDto> userRepository;

    /**
     *
     * @return
     */
    @Override
    public List<UserDto> list() {
        return userRepository.list();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public UserDto byId(Long id) {
        return userRepository.byId(id);
    }

    @Override
    public void update(UserDto t) {
        userRepository.update(t);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }
}
