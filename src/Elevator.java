/**
 * Elevator gets requests from the floors to be serviced
 * @author sarahlamonica
 *
 */
public class Elevator implements Runnable {
	Scheduler s;
	
	public Elevator(Scheduler s) {
		this.s = s;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true) {
			FloorButtonRequest request = s.getElevator();
			System.out.println(Thread.currentThread().getName() + " has been requested and elevator goes to floor " + request.getFloorNum() + " for pickup.");
			System.out.println("The passenger gets on the elevator at floor " + request.getFloorNum() + " and goes " + request.getDirection().toString() + " to Floor " + request.getDestinationFloor());
		}
		
	}
	
}
