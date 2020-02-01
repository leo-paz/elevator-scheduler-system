import java.util.ArrayList;
 
/**
 * This is the Scheduler class that extends Thread. This class allows the elevator to 
 * uses the input to assign the floor to the elevator while it also
 * coordinates with the floor buttons.
 * @version Iteration 1: Feb 1st 2020 
 * 
 */
public class Scheduler extends Thread {
	
	ArrayList<FloorButtonRequest> elevatorWaitingList = null;
	private int numOfRequestsCompleted = 0; //counts the number of elevators
	
	/**
	 * This is a getter method for all the floor in an ArrayList waiting to be serviced
	 * @return an array list of all the floor that need to be serviced
	 */
	public ArrayList<FloorButtonRequest> getElevatorWaitingList()
	{
		return elevatorWaitingList;
	}
	
	/**
	 * This is getter method for the all the request that were made for
	 * the elevator
	 * @return the total number of requests made as an integer
	 */
	public int getCompletedRequests() {
		return numOfRequestsCompleted;
	}

	/**
	 * Put an elevator on the scheduler when a user from a floor requests it.
	 * @param elevator
	 */
	public synchronized void scheduleElevator(FloorButtonRequest floor)
	{
        while (!(elevatorWaitingList == null)) { //wait if there is already a user (floor) being serviced. they should be serviced first
            try {
                wait();
            } catch (InterruptedException e) {
            	System.out.println(e.getStackTrace());
                return;
            }
        }
        
        elevatorWaitingList = new ArrayList<FloorButtonRequest>();
        elevatorWaitingList.add(floor);
	    try {
	    	Thread.sleep(1000);     // slow the process down
	    } catch (InterruptedException e) {
	    	System.out.println(e.getStackTrace());
	    	return;
	    }  // finished if there is an interrupt
        notifyAll();
	}
	
	/**
	 * Get an elevator when a user from a floor wants to use the elevator
	 * @return
	 */
	public synchronized FloorButtonRequest getElevator()
	{
		while(elevatorWaitingList == null) //if there are no users (floors) waiting to be serviced, wait
		{
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println(e.getStackTrace());
    	   		return null;
			}
		}
		
		FloorButtonRequest item = elevatorWaitingList.get(0);
	    elevatorWaitingList = null;
	     
	    try {
	          Thread.sleep(1000);     // slow down the process 
	    } catch (InterruptedException e) {
	    	System.out.println(e.getStackTrace());
	    	return null;
	    }
	    numOfRequestsCompleted++;
	    notifyAll();
	    return item;
	     
	}
}
