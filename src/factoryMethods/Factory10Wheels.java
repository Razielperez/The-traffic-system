package factoryMethods;

import builder.BuildCars;
import components.Road;
import components.Vehicle;
import utilities.VehicleType;

public class Factory10Wheels implements BuildCars {
    @Override
    public VehicleWheels getVehicle(Road road, String type) {
         if("PUBLIC".equalsIgnoreCase(type))
            return new Vehicle(road,VehicleType.tram);
        else if("WORK".equalsIgnoreCase(type))
            return new Vehicle(road,VehicleType.semitrailer);
        return null;
    }
}
