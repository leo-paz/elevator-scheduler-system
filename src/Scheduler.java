import java.util.ArrayList;

public class Scheduler extends Thread {
	
	ArrayList<Object> elevatorWaitingList = null;
	public boolean empty = true;
	int count = 0; //count number of elevators

	/**
	 * Put an elevator on the scheduler when a user from a floor requests it.
	 * @param elevator
	 */
	public synchronized void scheduleElevator(Object floor)
	{
        while (!empty) { //wait if there is already a user (floor) being serviced. they should be serviced first
            try {
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }
        
        elevatorWaitingList = new ArrayList<Object>();
        elevatorWaitingList.add(floor);
        empty = false; // the user (floor) has been serviced
	      try {
	          Thread.sleep(1000);     // slow it down 
	       } catch (InterruptedException e) {
	               return;
	       }  // finished if interrupted
        notifyAll();
	}
	
	/**
	 * Get an elevator when a user from a floor wants to use the elevator
	 * @return
	 */
	public synchronized Object getElevator()
	{
		while(empty) //if there are no users (floors) waiting to be serviced, wait
		{
			try {
            		wait();
			} catch (InterruptedException e) {
    	   			return null;
			}
		}
		
		 ArrayList<Object> item = elevatorWaitingList;
	     elevatorWaitingList = null;
	     empty = true;
	     
	      try {
	          Thread.sleep(1000);     // slow it down 
	       } catch (InterruptedException e) {
	               return null;
	       } 
	     notifyAll();
	     return item;
	     
	}
}
