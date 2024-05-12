package service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mapping.dtos.ReservationDto;
import repository.Repository;
import service.Service;

import java.util.List;
@ApplicationScoped
public class ReservationServiceImpl implements Service<ReservationDto> {
    @Inject
    @Named("Reservation")
    private Repository<ReservationDto> reservationRepository;
    @Override
    public List<ReservationDto> list() {
        return reservationRepository.list();
    }

    @Override
    public ReservationDto byId(Long id) {
        return reservationRepository.byId(id);
    }

    @Override
    public void update(ReservationDto t) {
        reservationRepository.update(t);
    }

    @Override
    public void delete(Long id) {
        reservationRepository.delete(id);
    }
}
