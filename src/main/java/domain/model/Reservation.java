package domain.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Reservation implements Serializable {
    private Long idReservation;
    private String name;
    private Double price;
    private String description;
    private Client client;
    private Vehicle vehicle;
    private LocalDateTime reservationInit;
    private LocalDateTime reservationFinal;
}
