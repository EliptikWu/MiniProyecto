package domain.model;

import jakarta.enterprise.context.SessionScoped;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@SessionScoped
public class User implements Serializable {
    private Long idUser;
    private String name;
    private String email;
    private String telephone;

}
