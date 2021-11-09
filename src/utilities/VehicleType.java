/**
 * 
 */
package utilities;


/**
 * @author Sophie Krimberg
 *
 */
public enum VehicleType {
	car(9), bus(6), bicycle(4), motorcycle(12), truck(8), tram(5), semitrailer(8);
	
	
	private int averageSpeed;
	
	
	VehicleType(int speed) {
		averageSpeed=speed; 
		
	}

	public int getAverageSpeed() {
		return averageSpeed;
	}
}
