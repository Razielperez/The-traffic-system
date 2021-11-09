package factoryMethods;

import components.Junction;
import components.LightedJunction;
import utilities.Utilities;

import java.util.Random;

public  class  FactoryJunction {
     public static Junction getJunction(String str)
     {
         if("CITY".equalsIgnoreCase(str))
             return new LightedJunction();
         else if("COUNTRY".equalsIgnoreCase(str))
             return (new Random().nextBoolean())? new Junction():new LightedJunction();
         return null;
     }
}
