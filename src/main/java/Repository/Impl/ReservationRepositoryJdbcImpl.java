package Repository.Impl;

import Domain.Dtos.ClientDto;
import Domain.Dtos.ReservationDto;
import Domain.Mapping.ClientMapper;
import Domain.Mapping.ReservationMapper;
import Domain.model.Client;
import Domain.model.Reservation;
import Domain.model.Vehicle;
import Exceptions.ServiceJdbcException;
import Repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepositoryJdbcImpl implements Repository<ReservationDto> {
    private Connection conn;
    private Reservation createReservation(ResultSet rs) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setIdReservation(rs.getLong("idReservation"));
        reservation.setName(rs.getString("name"));
        reservation.setDescription(rs.getString("description"));
        Client client = new Client();
        client.setIdClient(rs.getLong("idClient"));
        client.setName(rs.getString("idName"));
        client.setEmail(rs.getString("email"));
        client.setTelephone(rs.getString("telephone"));
        reservation.setClient(client);
        Vehicle vehicle = new Vehicle();
        vehicle.setIdVehicle(rs.getLong("idVehicle"));
        vehicle.setName(rs.getString("name"));
        vehicle.setAvailable(rs.getString("available"));
        vehicle.setPrice(rs.getDouble("price"));
        vehicle.setType(rs.getString("type"));
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
            sql = "UPDATE reservation SET name=?, description=?, client=?, vehicle=? WHERE idReservation=?";
        } else {
            sql = "INSERT INTO client (name, description, client, vehicle) VALUES(?,?,?,?)";
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, reservation.name());
            pstmt.setString(2, reservation.description());
            pstmt.setLong(3, reservation.client().getIdClient());
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


}