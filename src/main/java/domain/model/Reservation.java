package domain.model;

import jakarta.enterprise.context.SessionScoped;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@SessionScoped
public class Reservation implements Serializable {
    private Long idReservation;
    private String name;
    private Double price;
    private String description;
    private User user;
    private Vehicle vehicle;
    private LocalDateTime reservationInit;
    private LocalDateTime reservationFinal;
}
