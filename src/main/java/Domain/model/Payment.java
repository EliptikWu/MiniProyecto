package Domain.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Payment implements Serializable {
    private Long idPayment;
    private Long idVehicle;
    private Long idReservation;
    private Long idClient;
}
