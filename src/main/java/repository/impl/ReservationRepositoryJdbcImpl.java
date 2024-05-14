package repository.impl;

import annotations.MysqlConn;
import domain.enums.VehicleAvailable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mapping.dtos.ReservationDto;
import mapping.mapper.ReservationMapper;
import domain.enums.VehicleType;
import domain.model.User;
import domain.model.Reservation;
import domain.model.Vehicle;
import exceptions.ServiceJdbcException;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@RequestScoped
@Named("Reservation")
public class ReservationRepositoryJdbcImpl implements Repository<ReservationDto> {
    @Inject
    @MysqlConn
    private Connection conn;
    private Reservation createReservation(ResultSet rs) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setIdReservation(rs.getLong("idReservation"));
        reservation.setName(rs.getString("name"));
        reservation.setDescription(rs.getString("description"));
        User user = new User();
        user.setIdUser(rs.getLong("idUser"));
        user.setName(rs.getString("idName"));
        user.setEmail(rs.getString("email"));
        user.setTelephone(rs.getString("telephone"));
        reservation.setUser(user);
        Vehicle vehicle = new Vehicle();
        vehicle.setIdVehicle(rs.getLong("idVehicle"));
        vehicle.setName(rs.getString("name"));
        vehicle.setAvailable(VehicleAvailable.parse(rs.getString("available")));
        vehicle.setPrice(rs.getDouble("price"));
        vehicle.setType(VehicleType.parse(rs.getString("type")));
        reservation.setVehicle(vehicle);
        return reservation;
    }
    @Override
    public List<ReservationDto> list(){
        List<Reservation> reservationList = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from reservation")) {
            while (rs.next()) {
                Reservation reservation = createReservation(rs);
                reservationList.add(reservation);
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to list info");
        }
        return ReservationMapper.mapFrom(reservationList);
    }

    @Override
    public ReservationDto byId(Long id) {
        Reservation reservation = null;
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM reservation WHERE idReservation=?")) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    reservation = createReservation(rs);
                }
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to find info");
        }
        return ReservationMapper.mapFrom(reservation);
    }

    @Override
    public void update(ReservationDto reservation) {
        String sql;
        if (reservation.idReservation() != null && reservation.idReservation() > 0) {
            sql = "UPDATE reservation SET name=?, description=?, user=?, vehicle=? WHERE idReservation=?";
        } else {
            sql = "INSERT INTO user (name, description, user, vehicle) VALUES(?,?,?,?)";
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, reservation.name());
            pstmt.setString(2, reservation.description());
            pstmt.setLong(3, reservation.user().getIdUser());
            pstmt.setLong(4,reservation.vehicle().getIdVehicle());

            if (reservation.idReservation() != null && reservation.idReservation() > 0) {
                pstmt.setLong(4, reservation.idReservation());
            }
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM reservation WHERE idReservation=?")) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to delete info");
        }
    }

    @Override
    public void save(ReservationDto reservationDto) {
        String sql;
        Reservation reservation = ReservationMapper.mapFrom(reservationDto);
        if (reservation.getIdReservation() != null && reservation.getIdReservation() > 0) {
            sql = "UPDATE reservation SET name=?, price=?, description=?, " +
                    "user=?, vehicle=?, reservationInit=?, reservationFinal=? WHERE idReservation=?";
        } else {
            sql = "INSERT INTO reservation (name, price, description, user, vehicle, reservationInit, reservationFinal) VALUES (?,?,?,?,?,?,?);";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reservation.getName());
            stmt.setString(2, reservation.getDescription());
            stmt.setLong(3, reservation.getUser().getIdUser());
            stmt.setLong(4, reservation.getVehicle().getIdVehicle());
            if (reservation.getIdReservation() != null && reservation.getIdReservation() > 0) {
                stmt.setLong(4, reservation.getIdReservation());
            }
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }

    }
}