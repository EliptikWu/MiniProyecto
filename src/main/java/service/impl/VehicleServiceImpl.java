package service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mapping.dtos.VehicleDto;
import repository.Repository;
import service.Service;

import java.util.List;
/**
 * Implementation of the service for vehicle management.
 *
 * @author <a href="https://github.com/EliptikWu"
 */
@ApplicationScoped
public class VehicleServiceImpl implements Service<VehicleDto> {
    @Inject
    @Named("Vehicle")
    private Repository<VehicleDto> vehicleRepository;

    /**
     * Returns a list of VehicleDto objects.
     * @return  a list of VehicleDto objects that represent vehicles.
     */
    @Override
    public List<VehicleDto> list() {
        return vehicleRepository.list();
    }

    /**
     * Returns a VehicleDto object corresponding to the specified ID.
     *
     * @param id the ID of the vehicle you want to recover.
     * @return a VehicleDto object that represents the vehicle with the specified ID.
     */
    @Override
    public VehicleDto byId(Long id) {
        return vehicleRepository.byId(id);
    }

    /**
     * Saves a vehicle's information.
     *
     * @param t the VehicleDto object that contains the saved vehicle information.
     */
    @Override
    public void save(VehicleDto t) {
        vehicleRepository.save(t);
    }

    /**
     * Delete a vehicle based on their ID.
     *
     * @param id the ID of the vehicle to delete.
     */
    @Override
    public void delete(Long id) {
        vehicleRepository.delete(id);
    }
}
