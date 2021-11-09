package factoryMethods;

import builder.BuildCars;
import components.Road;
import components.Vehicle;
import utilities.VehicleType;

public class Factory4Wheels implements BuildCars {

    @Override
    public VehicleWheels getVehicle(Road road, String type) {
        if("PRIVATE".equalsIgnoreCase(type))
            return new Vehicle(road,VehicleType.car);
        else if("WORK".equalsIgnoreCase(type))
            return new Vehicle(road,VehicleType.truck);
        else if("PUBLIC".equalsIgnoreCase(type))
            return new Vehicle(road,VehicleType.bus);
        return null;
    }
}

