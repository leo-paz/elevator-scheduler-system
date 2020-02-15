import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

/**
 * This is class is test class for End State
 * @version Iteration 2: Feb 15th 2020 
 *
 */
class testEndCase {

	Elevator e;
	EndState es = new EndState(e);
	FloorButtonRequest fbs;
	//State 
	
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
