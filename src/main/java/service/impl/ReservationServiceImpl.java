package service.impl;

import mapping.dtos.ReservationDto;
import repository.Repository;
import service.Service;

import java.util.List;

public class ReservationServiceImpl implements Service<ReservationDto> {
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
    public void save(ReservationDto t) {
        reservationRepository.save(t);
    }

    @Override
    public void delete(Long id) {
        reservationRepository.delete(id);
    }
}
