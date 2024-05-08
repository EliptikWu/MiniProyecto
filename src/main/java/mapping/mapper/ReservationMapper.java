package mapping.mapper;

import mapping.Dtos.ReservationDto;
import domain.model.Reservation;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationMapper {
    public static ReservationDto mapFrom(Reservation reservationMapper){
        return new ReservationDto(reservationMapper.getIdReservation(),
                reservationMapper.getName(),
                reservationMapper.getPrice(),
                reservationMapper.getDescription(),
                reservationMapper.getClient(),
                reservationMapper.getVehicle(),
                reservationMapper.getReservationInit(),
                reservationMapper.getReservationFinal());
    }

    public static Reservation mapFrom(ReservationDto reservationMapper){
        return new Reservation(reservationMapper.idReservation(),
                reservationMapper.name(),
                reservationMapper.price(),
                reservationMapper.description(),
                reservationMapper.client(),
                reservationMapper.vehicle(),
                reservationMapper.reservationInit(),
                reservationMapper.reservationFinal());
    }

    public static List<ReservationDto> mapFrom(List<Reservation> reservationMapper){
        return reservationMapper.stream().map(ReservationMapper::mapFrom).collect(Collectors.toList());
    }

    public static List<Reservation> mapFromDto(List<ReservationDto> reservationMapper){
        return reservationMapper.stream().map(ReservationMapper::mapFrom).collect(Collectors.toList());
    }
}
