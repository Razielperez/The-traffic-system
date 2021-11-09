/**
 * raziel perez 316134956
 */
package components;


import Gui.CenterScreen;
import Inspection.BigBrother;
import Inspection.Moked;
import factoryMethods.VehicleWheels;

import utilities.Timer;
import utilities.Utilities;
import utilities.VehicleType;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Scanner;
/**
 * @author Sophie Krimberg
 *
 */
/**
 * @author krsof
 *
 */
public class Vehicle  extends Observable implements Cloneable, Utilities, Timer, VehicleWheels {
	private int id;
	private VehicleType vehicleType;
	private Route currentRoute;
	private RouteParts currentRoutePart;
	private int timeFromRouteStart;
	private static int objectsCount=1;
	private int timeOnCurrentPart;
	private Road lastRoad;
	private Color color_car;
	private String status;
	private  Moked moked;

	/**Random Constructor
	 * @param currentLocation
	 */
	public Vehicle (Road currentLocation)  {

		id=objectsCount++;
		vehicleType=currentLocation.getVehicleTypes()[getRandomInt(0,currentLocation.getVehicleTypes().length-1)];
		System.out.println();
		successMessage(this.toString());
		currentRoute=new Route(currentLocation, this); //creates a new route for the vehicle and checks it in
		lastRoad=currentLocation;
		status=null;
		color_car= Color.BLUE;
		addObserver(BigBrother.getInstance());
		moked=BigBrother.getInstance().getMoked();


	}
	public Vehicle(Vehicle other){
		id=other.id;
		vehicleType=other.vehicleType;
		successMessage(this.toString());
		currentRoute=other.currentRoute;
		lastRoad=other.lastRoad;
		status=null;
		color_car= other.color_car;
		moked=BigBrother.getInstance().getMoked();
		addObserver(BigBrother.getInstance());


	}
	public Vehicle clone(){return new Vehicle(this);}
	public void upgrade(Road road){
		id=objectsCount++;
		currentRoute=new Route(road,this);

	}
	public Vehicle (Road currentLocation,VehicleType type)  {

		id=objectsCount++;
		vehicleType=type;

		successMessage(this.toString());
		currentRoute=new Route(currentLocation, this); //creates a new route for the vehicle and checks it in
		lastRoad=currentLocation;
		status=null;
		moked=BigBrother.getInstance().getMoked();
		color_car= Color.BLUE;
		addObserver(BigBrother.getInstance());


	}

	/**
	 *
	 * @return color_car
	 */
	public Color getColor_car() {
		return color_car;
	}

	/**
	 *
	 * @param color_car
	 */
	public void setColor_car(Color color_car) {
		this.color_car = color_car;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}




	/**
	 * @return the vehicleType
	 */
	public VehicleType getVehicleType() {
		return vehicleType;
	}




	/**
	 * @param vehicleType the vehicleType to set
	 */
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}




	/**
	 * @return the currentRoute
	 */
	public Route getCurrentRoute() {
		return currentRoute;
	}




	/**
	 * @param currentRoute the currentRoute to set
	 */
	public void setCurrentRoute(Route currentRoute) {
		this.currentRoute = currentRoute;
	}




	/**
	 * @return the currentRoutePart
	 */
	public RouteParts getCurrentRoutePart() {
		return currentRoutePart;
	}




	/**
	 * @param currentRoutePart the currentRoutePart to set
	 */
	public void setCurrentRoutePart(RouteParts currentRoutePart) {
		this.currentRoutePart = currentRoutePart;
	}




	/**
	 * @return the timeFromRouteStart
	 */
	public int getTimeFromRouteStart() {
		return timeFromRouteStart;
	}




	/**
	 * @param timeFromRouteStart the timeFromRouteStart to set
	 */
	public void setTimeFromRouteStart(int timeFromRouteStart) {
		this.timeFromRouteStart = timeFromRouteStart;
	}




	/**
	 * @return the timeOnCurrentPart
	 */
	public int getTimeOnCurrentPart() {
		return timeOnCurrentPart;
	}




	/**
	 * @param timeOnCurrentPart the timeOnCurrentPart to set
	 */
	public void setTimeOnCurrentPart(int timeOnCurrentPart) {
		this.timeOnCurrentPart = timeOnCurrentPart;
	}




	/**
	 * @return the lastRoad
	 */
	public Road getLastRoad() {
		return lastRoad;
	}




	/**
	 * @param lastRoad the lastRoad to set
	 */
	public void setLastRoad(Road lastRoad) {
		this.lastRoad = lastRoad;
	}




	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}




	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}




	/**
	 * @return the objectsCount
	 */
	public static int getObjectsCount() {
		return objectsCount;
	}




	@Override
	public void incrementDrivingTime() {
		timeFromRouteStart++;
		timeOnCurrentPart++;
		move();
	}
	
	/**controls the vehicle moving from one route part to the next one
	 * 
	 */
	public void move() {
		if (currentRoutePart.canLeave(this)) {
			currentRoutePart.checkOut(this);
			currentRoute.findNextPart(this).checkIn(this);
			if (currentRoutePart instanceof Junction) {
				setChanged();
				notifyObservers();
//				readReport("C:\\Users\\razie\\IdeaProjects\\HOMEWORK4\\reports.txt");

			}
		}
		else {
			currentRoutePart.stayOnCurrentPart(this);
		}

	}
	
	
	@Override
	public String toString() {
		return new String("Vehicle "+id+": "+ getVehicleType().name()+", average speed: "+getVehicleType().getAverageSpeed());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false; 
	    if (getClass() != obj.getClass()) return false; 
	    if (! super.equals(obj)) return false;
		Vehicle other=(Vehicle)obj;
		if (this.currentRoute!=other.currentRoute||
			this.currentRoutePart!=other.currentRoutePart||
			this.id!=other.id||
			this.lastRoad!=other.lastRoad||
			this.status!=other.status||
			this.timeFromRouteStart!=other.timeFromRouteStart||
			this.timeOnCurrentPart!=other.timeOnCurrentPart||
			this.vehicleType!=other.vehicleType)
				return false;
		return true;
	}

	/**
	 * @param objectsCount the objectsCount to set
	 */
	public static void setObjectsCount(int objectsCount) {
		Vehicle.objectsCount = objectsCount;
	}

	public void readReport(){
		moked.ReportApproval(this);
	}




	@Override
	public void run() {
		while (true)
		{
			incrementDrivingTime();
			try
			{

				Thread.sleep(100);

			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			wait_element();
			if(getRandomBoolean())
				readReport();

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


}
