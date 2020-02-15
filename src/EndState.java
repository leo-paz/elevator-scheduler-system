/**
 * EndState class is the final state of the elevator, when there is no
 * more movement of the doors or between floors. 
 * @version Iteration 2: Feb 15th 2020 
 * 
 */

public class EndState extends State {
	
	/**
	 * Constructor for EndState
	 * @param ele is type Elevator class
	 */

	public EndState(Elevator ele) {
		super(ele);
	}

	
	@Override
	/**
	 * This method checks if the door is closing 
	 */
	public void moveDoor() {
		try {
			System.out.println("Closing doors.");
			Thread.sleep(CloseDoorTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * This method checks if the elevator is moving or 
	 * if all the trips have been completed
	 */
	public void moveElevator() {
		try {
			System.out.println("Elevator completed trips.");
			Thread.sleep(CloseDoorTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}

