package Domain.Dtos;

import Domain.model.Client;
import Domain.model.Vehicle;
import lombok.Builder;

@Builder
public record ReservationDto(Long idReservation,
                             String name,
                             String description,
                             Client client,
                             Vehicle vehicle) {
}
