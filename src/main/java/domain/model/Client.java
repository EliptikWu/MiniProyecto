package domain.model;

import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Client implements Serializable {
    private Long idClient;
    private String name;
    private String email;
    private String telephone;

}
