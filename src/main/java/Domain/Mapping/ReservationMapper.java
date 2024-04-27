package Domain.Mapping;

import Domain.Dtos.ReservationDto;
import Domain.model.Reservation;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationMapper {
    public static ReservationDto mapFrom(Reservation reservationMapper){
        return new ReservationDto(reservationMapper.getIdReservation(),
                reservationMapper.getName(),
                reservationMapper.getDescription(),
                reservationMapper.getClient(),
                reservationMapper.getVehicle());
    }

    public static Reservation mapFrom(ReservationDto reservationMapper){
        return new Reservation(reservationMapper.idReservation(),
                reservationMapper.name(),
                reservationMapper.description(),
                reservationMapper.client(),
                reservationMapper.vehicle());
    }

    public static List<ReservationDto> mapFrom(List<Reservation> reservationMapper){
        return reservationMapper.stream().map(ReservationMapper::mapFrom).collect(Collectors.toList());
    }

    public static List<Reservation> mapFromDto(List<ReservationDto> reservationMapper){
        return reservationMapper.stream().map(ReservationMapper::mapFrom).collect(Collectors.toList());
    }
}
