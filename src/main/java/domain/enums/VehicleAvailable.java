package domain.enums;

import java.util.Arrays;
import java.util.List;

public enum VehicleAvailable {

    AVAILABLE("Available"), NOTAVAILABLE("Not Available"), MAINTENANCE("Maintenance");
    private final String name;

    VehicleAvailable(String name) {
        this.name = name;
    }

    public List<String> getAvailable() {
        return Arrays.stream(VehicleAvailable.values()).map(VehicleAvailable::name).toList();
    }

    public static VehicleAvailable parse(String value) {
        return Arrays.stream(VehicleAvailable.values())
                .filter(e->e.name.equals(value)).findFirst().orElseThrow(()-> new IllegalArgumentException("Invalid value"));
    }
}
