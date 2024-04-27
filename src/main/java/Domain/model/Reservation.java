package Domain.model;

import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Reservation implements Serializable {
    private Long idReservation;
    private String name;
    private String description;
    private Client client;
    private Vehicle vehicle;
}
