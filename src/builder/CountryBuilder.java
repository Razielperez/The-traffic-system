package builder;

import components.*;
import factoryMethods.FactoryJunction;
import factoryMethods.factoryBuildCars;
import prototype.Workshop;
import utilities.Utilities;

import java.util.ArrayList;

public class CountryBuilder implements MapBuilder, Utilities {
    private Map map;
    int numOfVehicles;
    public CountryBuilder(int num){map=new Map();numOfVehicles=num;}
    @Override
    public void buildRoads() {
        ArrayList<Road> roads=new ArrayList<>();
        for (int i=0; i<map.getJunctions().size();i++) {
            for (int j=0; j<map.getJunctions().size();j++) {
                if(i==j) { continue; }
                if(getRandomBoolean())
                    roads.add(new Road(map.getJunctions().get(i), map.getJunctions().get(j)));
            }
        }
        map.setRoads(roads);
        map.turnLightsOn();


    }

    @Override
    public void buildJunctions() {
        ArrayList<Junction> junctions=new ArrayList<>();
        ArrayList<TrafficLights> lights=new ArrayList<>();
        for (int i=0;i<6;i++){
            Junction j=  FactoryJunction.getJunction("COUNTRY");
            junctions.add(j);
            if(j instanceof LightedJunction)
                lights.add(((LightedJunction) j).getLights());
        }
        map.setJunctions(junctions);
        map.setLights(lights);



    }

    @Override
    public void buildVehicles() {
        Workshop workshop=new Workshop();
        Road x=map.getRoads().get(1);
        ArrayList<Vehicle> vehicles=new ArrayList<>();
        ArrayList<Vehicle> types=new ArrayList<>();
        types.add((Vehicle) factoryBuildCars.getFactory(2).getVehicle(x,"fast"));
        types.add((Vehicle)factoryBuildCars.getFactory(10).getVehicle(x,"work"));
        types.add((Vehicle)factoryBuildCars.getFactory(4).getVehicle(x,"work"));
        types.add((Vehicle)factoryBuildCars.getFactory(4).getVehicle(x,"public"));
        types.add((Vehicle)factoryBuildCars.getFactory(4).getVehicle(x,"private"));
        Vehicle.setObjectsCount(1);
        while(vehicles.size()<numOfVehicles) {
			Road temp=map.getRoads().get(getRandomInt(0,map.getRoads().size()));//random road from the map
			if( temp.getEnabled())
                vehicles.add(workshop.makeCar(types.get(getRandomInt(0,types.size())),temp));
		}

        map.setVehicles(vehicles);


    }

    @Override
    public Map getMap() {
        return map;
    }
}
