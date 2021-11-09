/**
 * 
 */
package utilities;

/**
 * @author Sophie Krimberg
 *
 */
public interface Timer extends Runnable{
	public void incrementDrivingTime();
	@Override
	void run();
	public void wait_element();

}

//TODO Answer for bonus task 2: timer extends utilities, delete "implements utilities" from all classes.
