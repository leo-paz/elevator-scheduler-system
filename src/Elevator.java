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
			Object a = s.getElevator();
			System.out.println(Thread.currentThread().getName() + "has been requested " + " and elevator goes to Floor ");
		}
		
	}

	
	
}
