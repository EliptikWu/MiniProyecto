package service.Impl;

import mapping.Dtos.ClientDto;
import repository.Repository;
import service.Service;

import java.util.List;

public class ClientServiceImpl implements Service<ClientDto> {
    private Repository<ClientDto> clientRepository;
    @Override
    public List<ClientDto> list() {
        return clientRepository.list();
    }

    @Override
    public ClientDto byId(Long id) {
        return clientRepository.byId(id);
    }

    @Override
    public void save(ClientDto t) {
        clientRepository.save(t);
    }

    @Override
    public void delete(Long id) {
        clientRepository.delete(id);
    }
}
