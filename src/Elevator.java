/**
 * This is the Elevator class. It get elevator gets the requests from the floors to be serviced
 * @version Iteration 1: Feb 1st 2020
 *
 */
public class Elevator implements Runnable {
	Scheduler s;
	
	/**
	 * This is the constructor
	 * @param s is a type Scheduler
	 */
	public Elevator(Scheduler s) {
		this.s = s;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true) {
			FloorButtonRequest request = s.getElevator();//the destination floor the elevator is asked to get to
			System.out.println(Thread.currentThread().getName() + " has been requested and elevator goes to floor " + request.getFloorNum() + " for pickup.");
			System.out.println("The passenger gets on the elevator at floor " + request.getFloorNum() + " and goes " + request.getDirection().toString() + " to Floor " + request.getDestinationFloor());
		}
		
	}
	
}
