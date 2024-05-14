package service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mapping.dtos.ReservationDto;
import repository.Repository;
import service.Service;

import java.util.List;
/**
 * Implementation of the service for reservation management.
 *
 * @author <a href="https://github.com/EliptikWu"
 */
@ApplicationScoped
public class ReservationServiceImpl implements Service<ReservationDto> {
    @Inject
    @Named("Reservation")
    private Repository<ReservationDto> reservationRepository;

    /**
     * Returns a list of ReservationDto objects.
     * @return  a list of ReservationDto objects that represent reservations.
     */
    @Override
    public List<ReservationDto> list() {
        return reservationRepository.list();
    }

    /**
     * Returns a ReservationDto object corresponding to the specified ID.
     *
     * @param id the ID of the reservation you want to recover.
     * @return a ReservationDto object that represents the reservation with the specified ID.
     */
    @Override
    public ReservationDto byId(Long id) {
        return reservationRepository.byId(id);
    }

    /**
     * saves a reservation's information.
     *
     * @param t the ReservationDto object that contains the saved reservation information.
     */
    @Override
    public void save(ReservationDto t) {
        reservationRepository.save(t);
    }

    /**
     * Delete a reservation based on their ID.
     *
     * @param id the ID of the reservation to delete.
     */
    @Override
    public void delete(Long id) {
        reservationRepository.delete(id);
    }
}
