package service.impl;

import mapping.dtos.ClientDto;
import repository.Repository;
import service.Service;

import java.util.List;

/**
 *
 * @author <a href=""
 */
public class ClientServiceImpl implements Service<ClientDto> {
    private Repository<ClientDto> clientRepository;

    /**
     *
     * @return
     */
    @Override
    public List<ClientDto> list() {
        return clientRepository.list();
    }

    /**
     *
     * @param id
     * @return
     */
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
