package mapping.Dtos;

import lombok.Builder;

@Builder
public record VehicleDto(Long idVehicle,
                         String name,
                         Enum available,
                         Double price,
                         Enum type) {
}
