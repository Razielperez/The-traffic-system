package prototype;

import components.Road;
import components.Vehicle;

public class Workshop {
    public Vehicle makeCar(Vehicle basic, Road road){
        Vehicle up=basic.clone();
        up.upgrade(road);
        return up;





    }
}
