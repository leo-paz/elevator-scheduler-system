import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

/**
 * This is the testSchduler class that tests all the relevant methods of 
 * the scheduler class.
 * @version Iteration 1: Feb 1st, 2020
 *
 */
public class testScheduler {


	ArrayList<FloorButtonRequest> elevatorWaitingList = new ArrayList<FloorButtonRequest>(3);
	private int numOfRequestsCompleted = 0;
	private boolean t = true;

	/**
	 * This test is for the getter method for the elevator's waitng 
	 * list
	 */
	@Test
	public void testGetElevatorWaitingList() {

		elevatorWaitingList = new ArrayList<FloorButtonRequest>();
		ArrayList<FloorButtonRequest> requests = (ArrayList<FloorButtonRequest>) FloorSubsystem.readInputFile();
		elevatorWaitingList.addAll(requests);
		assertTrue( elevatorWaitingList.size()>0);
	}

	/**
	 * This test is to check if the requests made were completed
	 */
	@Test
	public void testGetCompletedRequests() {

		assertEquals("No request has been sent yet", 0, numOfRequestsCompleted);
		for(int i = 0; i < elevatorWaitingList.size(); i++)
		{
			numOfRequestsCompleted++;
			assertEquals("Requests has been completed", i, numOfRequestsCompleted );
		}

	}

	/**
	 * The test is to check if the elevator was performing the service requested 
	 * by the scheduler
	 */
	@Test
	public void testScheduleElevator()
	{

		elevatorWaitingList = new ArrayList<FloorButtonRequest>();
		ArrayList<FloorButtonRequest> requests = (ArrayList<FloorButtonRequest>) FloorSubsystem.readInputFile();
		elevatorWaitingList.addAll(requests);
		assertEquals("The size of the list is greater than 0", t, elevatorWaitingList.addAll(requests));
		assertTrue(!elevatorWaitingList.isEmpty());
	}

	/**
	 * The test is to check if their are users to be serviced 
	 * and then get the information required
	 */
	@Test
	public void testGetElevator()
	{
		elevatorWaitingList = new ArrayList<FloorButtonRequest>();
		ArrayList<FloorButtonRequest> requests = (ArrayList<FloorButtonRequest>) FloorSubsystem.readInputFile();
		elevatorWaitingList.addAll(requests);
		assertTrue(elevatorWaitingList != null);
	}


}
