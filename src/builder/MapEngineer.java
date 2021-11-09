package builder;

import components.Map;

public class MapEngineer {
    private MapBuilder builder;
    public MapEngineer(MapBuilder builder){
        this.builder = builder;
    }
    public Map getMap() {
        return builder.getMap();
    }
    public void constructMap() {
        builder.buildJunctions();
        builder.buildRoads();
        builder.buildVehicles();

    }

}
