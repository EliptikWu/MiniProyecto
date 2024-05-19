package repository.impl;

import annotations.MysqlConn;
import domain.enums.VehicleAvailable;
import domain.enums.VehicleType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mapping.dtos.VehicleDto;
import mapping.mapper.VehicleMapper;
import domain.model.Vehicle;
import exceptions.ServiceJdbcException;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
@Named("Vehicle")
public class VehicleRepositoryJdbcImpl implements Repository<VehicleDto> {
    @Inject
    @MysqlConn
    private Connection conn;
    private VehicleDto createVehicle(ResultSet rs) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setIdVehicle(rs.getLong(    "idUser"));
        vehicle.setName(rs.getString("name"));
        vehicle.setAvailable(VehicleAvailable.parse(rs.getString("available")));
        vehicle.setPrice(rs.getDouble("price"));
        vehicle.setType(VehicleType.parse(rs.getString("type")));
        return VehicleMapper.mapFrom(vehicle);
    }

    /**
     * Retrieves a list of vehicles from the database.
     *
     * @return a list of VehicleDto objects that represent vehicles.
     * @throws ServiceJdbcException if an error occurs while trying to list database information.
     */
    @Override
    public List<VehicleDto> list(){
        List<VehicleDto> vehicleList = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from vehicle")) {
            while (rs.next()) {
                VehicleDto ps = createVehicle(rs);
                vehicleList.add(ps);
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to list info");
        }
        return vehicleList;
    }

    /**
     * Retrieves a vehicle from the database by its ID.
     *
     * @param id the ID of the vehicle you want to retrieve.
     * @return a VehicleDto object that represents the vehicle with the specified ID.
     * @throws ServiceJdbcException if an error occurs while trying to find the information in the database.
     */
    @Override
    public VehicleDto byId(Long id) {
        VehicleDto vehicle = null;
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM vehicle WHERE idVehicle=?")) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    vehicle = createVehicle(rs);
                }
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to find info");
        }
        return vehicle;
    }

    /**
     * Updates a vehicle in the database.
     *
     * @param vehicle the VehicleDto object that contains the updated vehicle information.
     */
//  @Override
    public void update(VehicleDto vehicle) {
        String sql;
        if (vehicle.idVehicle() != null && vehicle.idVehicle() > 0) {
            sql = "UPDATE vehicle SET name=?, available=?, price=?, type=? WHERE idVehicle=?";
        } else {
            sql = "INSERT INTO user (name, email, telephone, price, type) VALUES(?,?,?,?,?)";
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vehicle.name());
            pstmt.setString(2, String.valueOf(vehicle.available()));
            pstmt.setDouble(3, vehicle.price());
            pstmt.setString(4, String.valueOf(vehicle.type()));

            if (vehicle.idVehicle() != null && vehicle.idVehicle() > 0) {
                pstmt.setLong(4, vehicle.idVehicle());
            }
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Delete a vehicle from the database by its ID.
     *
     * @param id the ID of the vehicle to delete.
     * @throws ServiceJdbcException if an error occurs while trying to delete information from the database.
     */
    @Override
    public void delete(Long id) {
        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM vehicle WHERE idVehicle = ?")) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to delete info");
        }
    }

    /**
     * Save a new vehicle or update an existing one in the database.
     *
     * @param vehicleDto the VehicleDto object that contains the vehicle information to save or update.
     * @throws ServiceJdbcException if an error occurs while trying to save or update information in the database.
     */
    @Override
    public void save(VehicleDto vehicleDto){
        String sql;
        Vehicle vehicle = VehicleMapper.mapFrom(vehicleDto);
        if(vehicle.getIdVehicle() != null && vehicle.getIdVehicle()>0){
            sql = "UPDATE vehicle SET name=?, available=?, price=?, type=? WHERE idVehicle=?";
        }else{
            sql = "INSERT INTO vehicle (name, available, price, type) VALUES (?,?,?,?);";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, vehicle.getName());
            stmt.setString(2, String.valueOf(vehicle.getAvailable()));
            stmt.setString(3, String.valueOf(vehicle.getPrice()));
            stmt.setString(4, String.valueOf(vehicle.getType()));
            if(vehicle.getIdVehicle() != null && vehicle.getIdVehicle()>0){
                stmt.setLong(4, vehicle.getIdVehicle());
            }
            stmt.executeUpdate();
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
}}