import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * The is the testFloorButtonRequest class that tests all 
 * methods relevant to the FloorSubsytem class
 * @version Iteration 1: Feb 1st, 2020
 *
 */
public class testFloorButtonRequest {

	private String time, des, floorNum ;  
	private Direction dir;
	FloorButtonRequest FBR = new FloorButtonRequest(time, floorNum, dir, des, false);
	
	/**
	 * This test verifies the getter method getTime, 
	 * that gets the time of the elevator
	 */
	@Test
	public void testGetTime() {
		FBR.setTime("1:30");
		assertEquals("The time is 1:30.", "1:30", FBR.getTime());
	}
	/**
	 * This test verifies the current floor number of the elevator
	 */
	@Test
	public void testFloorNum() {
		FBR.setFloorName("2");
		assertEquals("The floor number is 2.", "2", FBR.getFloorNum());
	}
	
	/**
	 * This test verifies the direction the elevator is going
	 */
	@Test
	public void testGetDirection() {
		FBR.setDirection(Direction.DOWN);
		assertEquals("The direction of the elevator is down", "down", FBR.getDirection().toString().toLowerCase());
	}
	
	/**
	 * This test verifies the destination floor number the elevator is going
	 */
	@Test
	public void testGetDestinationFloor() {
		FBR.setDestinationFloor("6");
		assertEquals("The destination floor is 6.", "6", FBR.getDestinationFloor());
	}
}
