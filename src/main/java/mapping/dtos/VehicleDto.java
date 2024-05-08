package mapping.dtos;

import lombok.Builder;

@Builder
public record VehicleDto(Long idVehicle,
                         String name,
                         Enum available,
                         Double price,
                         Enum type) {
}
