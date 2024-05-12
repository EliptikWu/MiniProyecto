package service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mapping.dtos.VehicleDto;
import repository.Repository;
import service.Service;

import java.util.List;
@ApplicationScoped
public class VehicleServiceImpl implements Service<VehicleDto> {
    @Inject
    @Named("Vehicle")
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
    public void update(VehicleDto t) {
        vehicleRepository.update(t);
    }

    @Override
    public void delete(Long id) {
        vehicleRepository.delete(id);
    }
}
