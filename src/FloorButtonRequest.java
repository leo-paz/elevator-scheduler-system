/**
 * This is the FloorButtonRequest class which has setter and getter methods for the elevator 
 * system as well as the up and down buttons pertaining to the floors.
 * 
 * @version Iteration 2: Feb 15th 2020
 *
 */
public class FloorButtonRequest {
	
	private String time; // can be changed to use the type Time
	private String floorNum;
	private Direction Direction;
	private String destinationFloorNum;
	private boolean isLastRequest;
	
	/**
	 * This is the constructor
	 * @param time is the time it takes for the elevator to finish the task given
	 * @param floorNum is the current floor of the elevator
	 * @param Direction is in which direction the elevator is moving 
	 * @param destinationFloorNum is the floor that elevator button was recorded to be
	 */
	public FloorButtonRequest(String time, String floorNum, Direction Direction, String destinationFloorNum, boolean isLastRequest){
		this.time = time;
		this.floorNum = floorNum;
		this.Direction = Direction;
		this.destinationFloorNum = destinationFloorNum;
		this.isLastRequest = isLastRequest;
	}
	
	/**
	 * This is a getter method for the last request flag
	 * @return a boolean specifying whether this is the last request or not
	 */
	public boolean isLastRequest() {
		return isLastRequest;
	}
	
	/**
	 * This is a setter method for the last request
	 */
	public void setIsLastRequest() {
		this.isLastRequest = true;
	}

	/**
	 * This is a getter method for the time
	 * @return a string with the time it took for the elevator to perform the task
	 */
	public String getTime() {
		return time;
	}

	/**
	 * This is a setter method for time
	 * @param time the time it will take for the elevator
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * This is a getter method for the current floor of the elevator  
	 * @return a string of the number of floor the elevator is at currently
	 */
	public String getFloorNum() {
		return floorNum;
	}

	/**
	 * This is a setter for the floor the elevator is at presently
	 * @param floorNum is a string that represents the current floor
	 */
	public void setFloorName(String floorNum) {
		this.floorNum = floorNum;
	}

	/**
	 * This is a getter method for the direction at which the elevator should travel
	 * @return the direction of the direction of the
	 * elevator at which it will be traveling to get to its destination
	 */
	public Direction getDirection() {
		return Direction;
	}

	/**
	 * This is the setter method for the direction at which the elevator should travel
	 * @param direction of the elevator at which it will be traveling to get to its destination
	 */
	public void setDirection(Direction direction) {
		Direction = direction;
	}

	/**
	 * This is the getter method for the desired(destination) floor
	 * @return the destination floor
	 */
	public String getDestinationFloor() {
		return destinationFloorNum;
	}

	/**
	 * This is the setter method for the desired floor
	 * @param destinationFloorNum is string of the number of the destination floor
	 */
	public void setDestinationFloor(String destinationFloorNum) {
		this.destinationFloorNum = destinationFloorNum;
	}
	
	
}