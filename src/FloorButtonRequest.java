
public class FloorButtonRequest {
	
	private String time; // can be changed to use the type Time
	private String floorNum;
	private Direction Direction;
	private String destinationFloorNum;
	
	public FloorButtonRequest(String time, String floorNum, Direction Direction, String destinationFloorNum){
		this.time = time;
		this.floorNum = floorNum;
		this.Direction = Direction;
		this.destinationFloorNum = destinationFloorNum;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFloorNum() {
		return floorNum;
	}

	public void setFloorName(String floorNum) {
		this.floorNum = floorNum;
	}

	public Direction getDirection() {
		return Direction;
	}

	public void setDirection(Direction direction) {
		Direction = direction;
	}

	public String getDestinationFloor() {
		return destinationFloorNum;
	}

	public void setDestinationFloor(String destinationFloorNum) {
		this.destinationFloorNum = destinationFloorNum;
	}
	
	
}