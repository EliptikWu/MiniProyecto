package Domain.Dtos;

import lombok.Builder;

@Builder
public record VehicleDto(Long idVehicle,
                         String name,
                         boolean available,
                         double price,
                         String type) {
}
