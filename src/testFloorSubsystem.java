import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;
/**
 * The is the testFloorSubsyetm class that tests all 
 *  methods relevant to the FloorSubsytem class
 *  @version Iteration 1: Feb 1st, 2020
 *
 */

public class testFloorSubsystem {
	
	Scheduler s;
	FloorSubsystem FSS = new FloorSubsystem(s);
	ArrayList<FloorButtonRequest> currRequest = new ArrayList<>(3);

	/**
	 * This test is to verify the direction of the elevator is 
	 * going up
	 */
	@Test
	public void testGetDirection() {
		String dir = "up";
		String str;
		str = Direction.UP.toString().toLowerCase();
		assertEquals("The elevator is going up",
				dir, FSS.getDirection(str).toString().toLowerCase());
	}
	
	/**
	 * This test is to check the input file is not empty and 
	 * can be parsed.
	 */
	@Test
	public void testReadInputFile() {
				
		currRequest = new ArrayList<FloorButtonRequest>();
		ArrayList<FloorButtonRequest> requests = (ArrayList<FloorButtonRequest>) FSS.readInputFile();
		currRequest.addAll(requests);
		assertNotNull(currRequest); 
		
		assertTrue("There are requests", currRequest.size() > 0);
		
	}
}
