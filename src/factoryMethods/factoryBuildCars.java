package factoryMethods;

import builder.BuildCars;

public class factoryBuildCars {
    public static BuildCars getFactory(int x){
        if(x==2)
            return new Factory2Wheels();
        else if(x==4)
            return new Factory4Wheels();
        else if(x==10)
            return new Factory10Wheels();
        return null;
    }
}
