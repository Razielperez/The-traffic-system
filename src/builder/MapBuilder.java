package builder;

import components.Map;

public interface MapBuilder {
    public void buildRoads();
    public void buildJunctions();
    public void buildVehicles();
    public Map getMap();

    }
