package service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mapping.dtos.ClientDto;
import repository.Repository;
import service.Service;

import java.util.List;

/**
 *
 * @author <a href=""
 */
@ApplicationScoped
public class ClientServiceImpl implements Service<ClientDto> {
    @Inject
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
