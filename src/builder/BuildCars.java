package builder;

import components.Road;
import factoryMethods.VehicleWheels;

public interface BuildCars {
    public VehicleWheels getVehicle(Road road, String type);
}
