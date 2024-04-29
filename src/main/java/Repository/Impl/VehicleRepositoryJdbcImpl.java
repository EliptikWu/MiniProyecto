package Repository.Impl;

import Domain.Dtos.ClientDto;
import Domain.Dtos.VehicleDto;
import Domain.Mapping.ClientMapper;
import Domain.Mapping.VehicleMapper;
import Domain.model.Client;
import Domain.model.Vehicle;
import Exceptions.ServiceJdbcException;
import Repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepositoryJdbcImpl implements Repository<VehicleDto> {
    private Connection conn;
    private VehicleDto createVehicle(ResultSet rs) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setIdVehicle(rs.getLong("idClient"));
        vehicle.setName(rs.getString("name"));
        vehicle.setAvailable(rs.getString("available"));
        vehicle.setPrice(rs.getDouble("price"));
        vehicle.setType(rs.getString("type"));
        return VehicleMapper.mapFrom(vehicle);
    }
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

    @Override
    public void update(VehicleDto vehicle) {
        String sql;
        if (vehicle.idVehicle() != null && vehicle.idVehicle() > 0) {
            sql = "UPDATE vehicle SET name=?, available=?, price=?, type=? WHERE idVehicle=?";
        } else {
            sql = "INSERT INTO client (name, email, telephone, price, type) VALUES(?,?,?,?,?)";
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vehicle.name());
            pstmt.setString(2, vehicle.available());
            pstmt.setDouble(3, vehicle.price());
            pstmt.setString(4,vehicle.type());

            if (vehicle.idVehicle() != null && vehicle.idVehicle() > 0) {
                pstmt.setLong(4, vehicle.idVehicle());
            }
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM vehicle WHERE idVehicle = ?")) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to delete info");
        }
    }


}