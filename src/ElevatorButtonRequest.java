
public class ElevatorButtonRequest {
	
	private String time; // can be changed to use the type Time
	private String currFloor;
	private String destFloorNum;
	
	public ElevatorButtonRequest(String time, String currFloor, String destFloorNum){
		this.time = time;
		this.currFloor = currFloor;
		this.destFloorNum = destFloorNum;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCurrFloorNum() {
		return currFloor;
	}

	public void setFloorName(String currFloor) {
		this.currFloor = currFloor;
	}

	public String getDestinationFloor() {
		return destFloorNum;
	}

	public void setDestinationFloor(String destinationFloorNum) {
		this.destFloorNum = destinationFloorNum;
	}
	
	
}