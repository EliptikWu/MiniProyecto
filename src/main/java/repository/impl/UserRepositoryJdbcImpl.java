package repository.impl;

import annotations.MysqlConn;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mapping.dtos.UserDto;
import mapping.mapper.UserMapper;
import domain.model.User;
import exceptions.ServiceJdbcException;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@RequestScoped
@Named("User")
public class UserRepositoryJdbcImpl implements Repository<UserDto> {
    @Inject
    @MysqlConn
    private Connection conn;
    private UserDto createUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setIdUser(rs.getLong("idUser"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setTelephone(rs.getString("telephone"));
        return UserMapper.mapFrom(user);
    }

    /**
     * Retrieves a list of users from the database.
     *
     * @return a list of UserDto objects that represent users.
     * @throws ServiceJdbcException if an error occurs while trying to list database information.
     */
    @Override
    public List<UserDto> list(){
        List<UserDto> userList = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from user")) {
            while (rs.next()) {
                UserDto ps = createUser(rs);
                userList.add(ps);
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to list info");
        }
        return userList;
    }

    /**
     * Retrieves a user from the database by its ID.
     *
     * @param id the ID of the user you want to retrieve.
     * @return a UserDto object that represents the user with the specified ID.
     * @throws ServiceJdbcException if an error occurs while trying to find the information in the database.
     */
    @Override
    public UserDto byId(Long id) {
        UserDto user = null;
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user WHERE idUser=?")) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = createUser(rs);
                }
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to find info");
        }
        return user;
    }

    /**
     * Updates a user in the database.
     *
     * @param user the UserDto object that contains the updated user information.
     */
    //@Override
    public void update(UserDto user) {
        String sql;
        if (user.idUser() != null && user.idUser() > 0) {
            sql = "UPDATE user SET name=?, email=?, telephone=? WHERE idUser=?";
        } else {
            sql = "INSERT INTO user (name, email, telephone) VALUES(?,?,?)";
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.name());
            pstmt.setString(2, user.email());
            pstmt.setString(3, user.telephone());

            if (user.idUser() != null && user.idUser() > 0) {
                pstmt.setLong(4, user.idUser());
            }
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Delete a user from the database by its ID.
     *
     * @param id the ID of the user to delete.
     * @throws ServiceJdbcException if an error occurs while trying to delete information from the database.
     */
    @Override
    public void delete(Long id) {
        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM user WHERE idUser = ?")) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to delete info");
        }
    }

    /**
     * Save a new user or update an existing one in the database.
     *
     * @param userDto the UserDto object that contains the user information to save or update.
     * @throws ServiceJdbcException if an error occurs while trying to save or update information in the database.
     */
    @Override
    public void save(UserDto userDto){
        String sql;
        User user = UserMapper.mapFrom(userDto);
        if(user.getIdUser() != null && user.getIdUser()>0){
            sql = "UPDATE user SET name=?, email=?, telephone=? WHERE idUser=?";
        }else{
            sql = "INSERT INTO user (name, email, telephone) VALUES (?,?,?);";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getTelephone());
            if(user.getIdUser() != null && user.getIdUser()>0){
                stmt.setLong(4, user.getIdUser());
            }
            stmt.executeUpdate();
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }


}