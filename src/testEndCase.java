import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

/**
 * This is class is test class for End State
 * @version Iteration 2: Feb 15th 2020 
 *
 */
public class testEndCase {

	Elevator e;
	EndState es = new EndState(e);
	FloorButtonRequest fbs;
	Scheduler s = new Scheduler();
	//State 
	
	public testEndCase()
	{
		fbs = new FloorButtonRequest("5:00", "6", Direction.UP, "5", true);
		e = new Elevator(s);
	}
	
	@Test
	/**
	 * This method tests MoveDoor and MoveElevator as they 
	 * both check for the last request and are in the 
	 * last state.
	 */
	public void testLastRequest() {
		
		if (fbs.isLastRequest() == true) {
			e.setState(es);
			assertEquals("It is the last request", true, fbs.isLastRequest());	
		}
		 	
	}
	

}
