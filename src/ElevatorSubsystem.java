
/**
 * This is the ElevatorSubSystem class that is creating 4 threads for 4 different 
 * elevators.
 * @version Iteration 3: March 7, 2020
 *
 */
public class ElevatorSubsystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			Thread a, b, c, d;
			a = new Thread(new Elevator(1000,"Elevator NO.1"),"Elevator NO.1");
			b = new Thread(new Elevator(1001,"Elevator NO.2"),"Elevator NO.2");
			c = new Thread(new Elevator(1002,"Elevator NO.3"),"Elevator NO.3");
			d = new Thread(new Elevator(1003,"Elevator NO.4"),"Elevator NO.4");
			a.start();
			b.start();
			c.start();
			d.start();
			
		

	}

}
