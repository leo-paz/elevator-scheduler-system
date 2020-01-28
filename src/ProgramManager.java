
public class ProgramManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//main method will wait for user input in the form of .csv file
		Thread producer, consumer;
		Scheduler s = new Scheduler();
		producer = new Thread(new FloorSubsystem(s), "Floor");
		consumer = new Thread(new Elevator(s),"Elevator");
		producer.start();
		consumer.start();


	}

}
