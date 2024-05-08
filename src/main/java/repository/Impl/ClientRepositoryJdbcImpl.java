package repository.Impl;

import domain.model.Vehicle;
import mapping.Dtos.ClientDto;
import mapping.Dtos.VehicleDto;
import mapping.mapper.ClientMapper;
import domain.model.Client;
import exceptions.ServiceJdbcException;
import mapping.mapper.VehicleMapper;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryJdbcImpl implements Repository<ClientDto> {

    private Connection conn;
    private ClientDto createClient(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setIdClient(rs.getLong("idClient"));
        client.setName(rs.getString("name"));
        client.setEmail(rs.getString("email"));
        client.setTelephone(rs.getString("telephone"));
        return ClientMapper.mapFrom(client);
    }
    @Override
    public List<ClientDto> list(){
        List<ClientDto> clientList = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from client")) {
            while (rs.next()) {
                ClientDto ps = createClient(rs);
                clientList.add(ps);
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to list info");
        }
        return clientList;
    }

    @Override
    public ClientDto byId(Long id) {
        ClientDto client = null;
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM client WHERE idClient=?")) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    client = createClient(rs);
                }
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to find info");
        }
        return client;
    }

    @Override
    public void update(ClientDto client) {
        String sql;
        if (client.idClient() != null && client.idClient() > 0) {
            sql = "UPDATE client SET name=?, email=?, telephone=? WHERE idClient=?";
        } else {
            sql = "INSERT INTO client (name, email, telephone) VALUES(?,?,?)";
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, client.name());
            pstmt.setString(2, client.email());
            pstmt.setString(3, client.telephone());

            if (client.idClient() != null && client.idClient() > 0) {
                pstmt.setLong(4, client.idClient());
            }
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM client WHERE idClient = ?")) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to delete info");
        }
    }
    @Override
    public void save(ClientDto clientDto){
        String sql;
        Client client = ClientMapper.mapFrom(clientDto);
        if(client.getIdClient() != null && client.getIdClient()>0){
            sql = "UPDATE client SET name=?, email=?, telephone=? WHERE idClient=?";
        }else{
            sql = "INSERT INTO client (name, email, telephone) VALUES (?,?,?);";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getEmail());
            stmt.setString(3, client.getTelephone());
            if(client.getIdClient() != null && client.getIdClient()>0){
                stmt.setLong(4, client.getIdClient());
            }
            stmt.executeUpdate();
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }


}