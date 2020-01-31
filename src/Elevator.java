/**
 * Elevator gets requests from the floors to be serviced
 * @author sarahlamonica
 *
 */
public class Elevator implements Runnable {
	Scheduler s;
	
	public Elevator(Scheduler s)
	{
		this.s = s;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true)
		{
			FloorButtonRequest a = s.getElevator();
			System.out.println(Thread.currentThread().getName() + " has been requested " + " and elevator goes " + a.getDirection().toString() + " to Floor: " + a.getFloorNum());
			System.out.println("User gets on the elevator and the elevator goes to Floor " + a.getDestinationFloor());
		}
		
	}

	
	
}
