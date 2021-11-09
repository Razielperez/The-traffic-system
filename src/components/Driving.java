/**
 * raziel perez 316134956
 */


package components;

import java.util.ArrayList;

import Gui.CenterScreen;
import Gui.myPanel;
import Inspection.BigBrother;
import state.AllReportsOk;
import state.NotAllReportsOk;
import state.State;
import utilities.Timer;
import utilities.Utilities;

/**Traffic control game
 * @author Sophie Krimberg
 *
 */
public class Driving implements Utilities, Timer{
	private Map map;
	private ArrayList<Vehicle> vehicles;
	private int drivingTime;
	private ArrayList<Timer> allTimedElements;
	private myPanel mainPanel;
	private State state=new AllReportsOk();
	
	/**Constructor
	 * @param junctionsNum quantity of junctions
	 * @param numOfVehicles quantity of vehicles
	 */
	public Driving(int junctionsNum, int numOfVehicles) {

		vehicles=new ArrayList<Vehicle>();
		allTimedElements=new ArrayList<Timer>();
		drivingTime=0;
		map=new Map(junctionsNum);

		System.out.println("\n================= CREATING VEHICLES =================");

		while(vehicles.size()<numOfVehicles) {
			Road temp=map.getRoads().get(getRandomInt(0,map.getRoads().size()));//random road from the map
			if( temp.getEnabled())
				vehicles.add(new Vehicle(temp));
		}
		map.setVehicles(vehicles);

		allTimedElements.addAll(vehicles);

		for (TrafficLights light: map.getLights()) {
			if (light.getTrafficLightsOn()) {
				allTimedElements.add(light);
			}
		}
	}
	public Driving() {
		allTimedElements=new ArrayList<Timer>();
		drivingTime=0;
	}

	
	/**
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map map) {
		this.map = map;
		allTimedElements.addAll(map.getVehicles());
		for(TrafficLights t:map.getLights())
			if(t.getTrafficLightsOn())
				allTimedElements.add(t);
	}

	public void state(){
		if(BigBrother.getInstance().getMoked().getReports().isEmpty())
			this.state=new AllReportsOk();
		else
			this.state=new NotAllReportsOk();
	}
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	/**
	 * @return the vehicles
	 */
	public ArrayList<Vehicle> getVehicles() {
		return map.getVehicles();
	}

	/**
	 //* @param vehicles the vehicles to set
	 */
//	public void setVehicles(ArrayList<Vehicle> vehicles) {
//		this.vehicles = vehicles;
//	}

	public void setMainPanel(myPanel mainPanel) { this.mainPanel = mainPanel; }

	/**
	 * @return the drivingTime
	 */
	public int getDrivingTime() {
		return drivingTime;
	}

	/**
	 * @param drivingTime the drivingTime to set
	 */
	public void setDrivingTime(int drivingTime) {
		this.drivingTime = drivingTime;
	}

	/**
	 * @return the allTimedElements
	 */
	public ArrayList<Timer> getAllTimedElements() {
		return allTimedElements;
	}

	/**
	 * @param allTimedElements the allTimedElements to set
	 */
	public void setAllTimedElements(ArrayList<Timer> allTimedElements) {
		this.allTimedElements = allTimedElements;
	}

	/**method runs the game for given quantity of turns
	 * @param turns
	 */
	public void drive(int turns) {
		System.out.println("\n================= START DRIVING=================");

		drivingTime=0;
		for (int i=0; i<turns;i++) {
			incrementDrivingTime();
		}
	}

	@Override
	public void incrementDrivingTime() {
		System.out.println("\n***************TURN "+drivingTime++ +"***************");
		for(Timer element: allTimedElements) {
			System.out.println(element);
			(new Thread(element)).start();
			System.out.println();
		}
		
	}

	@Override
	public void run() {
		incrementDrivingTime();
		while (true)
			{
				mainPanel.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			wait_element();
			state();
			}
	}
	public synchronized void wait_element( ){
		while (CenterScreen.isStop){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	public synchronized void resumeAll(){
		try {
			notify();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for(Timer element: allTimedElements)
			synchronized (element){
				element.notify();
			}



	}

	@Override
	public String toString() {
		return "Driving [map=" + map +  ", drivingTime=" + drivingTime + ", allTimedElements="
				+ allTimedElements + "]";
	}

}
