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
public class Payment implements Serializable {
    private Long idPayment;
    private Long idVehicle;
    private Long idReservation;
    private Long idUser;
}
