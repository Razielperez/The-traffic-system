package builder;

import components.Junction;
import components.Road;
import components.Vehicle;

import java.util.ArrayList;

public interface MapPlan {
    public void setJunctions(ArrayList<Junction> junctions);
    public void setRoads(ArrayList<Road> roads);
    public void setVehicles(ArrayList<Vehicle> vehicles);
}
