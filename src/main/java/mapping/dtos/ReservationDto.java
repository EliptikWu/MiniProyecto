package mapping.dtos;

import domain.model.Client;
import domain.model.Vehicle;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReservationDto(Long idReservation,
                             String name,
                             Double price,
                             String description,
                             Client client,
                             Vehicle vehicle,
                             LocalDateTime reservationInit,
                             LocalDateTime reservationFinal) {
}
