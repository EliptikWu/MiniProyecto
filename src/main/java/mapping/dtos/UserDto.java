package mapping.dtos;

import lombok.Builder;

@Builder
public record UserDto(Long idUser,
                        String name,
                        String email,
                        String telephone) {
}
