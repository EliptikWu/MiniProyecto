package Domain.model;

import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Vehicle implements Serializable {
    private Long idVehicle;
    private String name;
    private String available;
    private double price;
    private String type;


}
