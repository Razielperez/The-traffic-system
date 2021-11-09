package factoryMethods;

import builder.BuildCars;
import components.Road;
import components.Vehicle;
import utilities.VehicleType;

public class Factory2Wheels implements BuildCars {
    @Override
    public VehicleWheels getVehicle(Road road, String type) {
        if("FAST".equalsIgnoreCase(type))
            return new Vehicle(road, VehicleType.motorcycle);
        else if("SLOW".equalsIgnoreCase(type))
            return new Vehicle(road,VehicleType.bicycle);
        return null;
    }

}
