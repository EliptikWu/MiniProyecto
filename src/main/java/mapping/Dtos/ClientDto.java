package mapping.Dtos;

import lombok.Builder;

@Builder
public record ClientDto(Long idClient,
                        String name,
                        String email,
                        String telephone) {
}
