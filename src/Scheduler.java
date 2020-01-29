import java.util.ArrayList;

public class Scheduler extends Thread {
	
	ArrayList<FloorButtonRequest> elevatorWaitingList = null;
	int count = 0; //count number of elevators

	/**
	 * Put an elevator on the scheduler when a user from a floor requests it.
	 * @param elevator
	 */
	public synchronized void scheduleElevator(FloorButtonRequest floor)
	{
        while (!elevatorWaitingList.isEmpty()) { //wait if there is already a user (floor) being serviced. they should be serviced first
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
	    	Thread.sleep(1000);     // slow it down 
	    } catch (InterruptedException e) {
	    	System.out.println(e.getStackTrace());
	    	return;
	    }  // finished if interrupted
        notifyAll();
	}
	
	/**
	 * Get an elevator when a user from a floor wants to use the elevator
	 * @return
	 */
	public synchronized FloorButtonRequest getElevator()
	{
		while(elevatorWaitingList.isEmpty()) //if there are no users (floors) waiting to be serviced, wait
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
	          Thread.sleep(1000);     // slow it down 
	    } catch (InterruptedException e) {
	    	System.out.println(e.getStackTrace());
	    	return null;
	    } 
	    notifyAll();
	    return item;
	     
	}
}
