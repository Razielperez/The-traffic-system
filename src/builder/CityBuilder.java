package builder;

import components.*;
import factoryMethods.FactoryJunction;
import factoryMethods.factoryBuildCars;
import prototype.Workshop;
import utilities.Utilities;

import java.util.ArrayList;

public class CityBuilder implements MapBuilder, Utilities {
    int numOfVehicles;
    private Map map;
    public CityBuilder(int num){map=new Map();numOfVehicles=num;}

    @Override
    public void buildRoads() {
        ArrayList<Road> roads=new ArrayList<>();
        for (int i=0; i<map.getJunctions().size();i++) {
            for (int j=0; j<map.getJunctions().size();j++) {
                if(i==j) {

                    continue;
                }
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
        for (int i=0;i<12;i++){
           LightedJunction j= (LightedJunction) FactoryJunction.getJunction("city");
           junctions.add(j);
           lights.add(j.getLights());
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
        types.add((Vehicle)factoryBuildCars.getFactory(2).getVehicle(x,"slow"));
        types.add((Vehicle)factoryBuildCars.getFactory(10).getVehicle(x,"public"));
        types.add((Vehicle)factoryBuildCars.getFactory(4).getVehicle(x,"public"));
        types.add((Vehicle)factoryBuildCars.getFactory(4).getVehicle(x,"private"));
        Vehicle.setObjectsCount(1);
        while(vehicles.size()<numOfVehicles) {
            Road temp=map.getRoads().get(getRandomInt(0,map.getRoads().size()));//random road from the map
            if( temp.getEnabled())
                vehicles.add(workshop.makeCar(types.get(getRandomInt(0,types.size())),temp));
        }
//        for(int i=0;i<10;i++){
//            Road road=map.getRoads().get(getRandomInt(0,map.getRoads().size()));
//            vehicles.add(workshop.makeCar(types.get(getRandomInt(0,types.size())),road));
//        }
        map.setVehicles(vehicles);



    }

    @Override
    public Map getMap() {
        return map;
    }
}
