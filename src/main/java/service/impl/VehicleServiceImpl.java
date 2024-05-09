package service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mapping.dtos.VehicleDto;
import repository.Repository;
import service.Service;

import java.util.List;
@ApplicationScoped
public class VehicleServiceImpl implements Service<VehicleDto> {
    @Inject
    private Repository<VehicleDto> vehicleRepository;
    @Override
    public List<VehicleDto> list() {
        return vehicleRepository.list();
    }

    @Override
    public VehicleDto byId(Long id) {
        return vehicleRepository.byId(id);
    }

    @Override
    public void save(VehicleDto t) {
        vehicleRepository.save(t);
    }

    @Override
    public void delete(Long id) {
        vehicleRepository.delete(id);
    }
}
