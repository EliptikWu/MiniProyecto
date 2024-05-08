package domain.enums;

import java.util.Arrays;
import java.util.List;

public enum VehicleType {
    CARRO("carro"), MOTO("moto");

    private final String name;

    VehicleType(String name) {
        this.name = name;
    }

    public List<String> getVehicleTypes() {
        return Arrays.stream(VehicleType.values()).map(VehicleType::name).toList();
    }

    public static VehicleType parse(String value) {
        return Arrays.stream(VehicleType.values())
                .filter(e->e.name.equals(value)).findFirst().orElseThrow(()-> new IllegalArgumentException("valor invalido"));
    }
}
