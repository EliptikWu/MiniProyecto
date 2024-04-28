package Domain.Mapping;

import Domain.Dtos.VehicleDto;
import Domain.model.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleMapper {
    public static VehicleDto mapFrom(Vehicle vehicleMapper) {
        return new VehicleDto(vehicleMapper.getIdVehicle(),
                vehicleMapper.getName(),
                vehicleMapper.isAvailable(),
                vehicleMapper.getPrice(),
                vehicleMapper.getType());
    }

    public static Vehicle mapFrom(VehicleDto vehicleMapper){
        return new Vehicle(vehicleMapper.idVehicle(),
                vehicleMapper.name(),
                vehicleMapper.available(),
                vehicleMapper.price(),
                vehicleMapper.type());
    }
    public static List<VehicleDto> mapFrom(List<Vehicle> vehicleMapper){
        return vehicleMapper.stream().map(VehicleMapper::mapFrom).collect(Collectors.toList());
    }

    public static List<Vehicle> mapFromDto(List<VehicleDto> vehicleMapper){
        return vehicleMapper.stream().map(VehicleMapper::mapFrom).collect(Collectors.toList());
    }
}
