import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertTrue;

/**
 * This is class is test class for Move and Stop State
 * @version Iteration 2: Feb 15th 2020 
 *
 */

import org.junit.jupiter.api.Test;

class testMoveState {
	Scheduler s;
	Elevator e = new Elevator(s);
	private String time; 
	private String floorNum;
	private Direction Direction;
	private String destinationFloorNum;
	private boolean isLastRequest;
	FloorButtonRequest fbs = new FloorButtonRequest(time,floorNum, Direction, destinationFloorNum, isLastRequest);
	MoveState mv = new MoveState(e);
	State currState;
	StopState ss = new StopState(e);

	/**
	 * Method verifies the last request and checks whether 
	 * the current state is in either Stop or Move State 	
	 */
	@Test
	public void testMoveStopState() {
		fbs.setIsLastRequest();
		if (fbs.isLastRequest() == false) {
			if (currState == mv) {
				currState = mv;
				assertTrue("Current state is MoveState", currState == mv);
			}
			else {	
				currState = ss;
				assertTrue("Current state is Stop State", currState == ss);
			}
		}
	}

}
