/**
 * This is the main method of the project. That create all the instance required
 * for the Elevator system to function.  
 * @version Iteration 1: Feb 1st 2020
 * 
 */
public class ProgramManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//main method will wait for user input in the form of .txt file
		Thread producer, consumer;
		Scheduler s = new Scheduler();
		producer = new Thread(new FloorSubsystem(s), "Floor");
		consumer = new Thread(new Elevator(s),"Elevator");
		producer.start();
		consumer.start();

	}

}
