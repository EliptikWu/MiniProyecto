package Domain.Mapping;

import Domain.Dtos.ClientDto;
import Domain.model.Client;

import java.util.List;
import java.util.stream.Collectors;

public class ClientMapper {
    public static ClientDto mapFrom(Client clientMapper){
        return new ClientDto(clientMapper.getIdClient(),
                clientMapper.getName(),
                clientMapper.getEmail(),
                clientMapper.getTelephone()
                );
    }

    public static Client mapFrom(ClientDto clientMapper){
        return new Client(clientMapper.idClient(),
                clientMapper.name(),
                clientMapper.email(),
                clientMapper.telephone());
    }

    public static List<ClientDto> mapFrom(List<Client> clientMapper){
        return clientMapper.stream().map(ClientMapper::mapFrom).collect(Collectors.toList());
    }

    public static List<Client> mapFromDto(List<ClientDto> clientMapper){
        return clientMapper.stream().map(ClientMapper::mapFrom).collect(Collectors.toList());
    }
}
