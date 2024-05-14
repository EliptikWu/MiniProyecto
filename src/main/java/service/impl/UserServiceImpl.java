package service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mapping.dtos.UserDto;
import repository.Repository;
import service.Service;

import java.util.List;

/**
 * Implementation of the service for user management.
 *
 * @author <a href="https://github.com/EliptikWu"
 */
@ApplicationScoped
public class UserServiceImpl implements Service<UserDto> {
    @Inject
    @Named("User")
    private Repository<UserDto> userRepository;

    /**
     * Returns a list of UserDto objects.
     * @return  a list of UserDto objects that represent users.
     */
    @Override
    public List<UserDto> list() {
        return userRepository.list();
    }

    /**
     * Returns a UserDto object corresponding to the specified ID.
     *
     * @param id the ID of the user you want to recover.
     * @return a UserDto object that represents the user with the specified ID.
     */
    @Override
    public UserDto byId(Long id) {
        return userRepository.byId(id);
    }

    /**
     * Saves a user's information.
     *
     * @param t the UserDto object that contains the saved user information.
     */
    @Override
    public void save(UserDto t) {
        userRepository.save(t);
    }

    /**
     * Delete a user based on their ID.
     *
     * @param id the ID of the user to delete.
     */
    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }
}
