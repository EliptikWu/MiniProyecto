package mapping.dtos;

import lombok.Builder;

@Builder
public record ClientDto(Long idClient,
                        String name,
                        String email,
                        String telephone) {
}
