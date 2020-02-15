/**
 * EndState class is the final state of the elevator, when there is no
 * more movement of the doors or between floors. 
 * @version Iteration 2: Feb 14th 2020 
 * 
 */

public class EndState extends State {

	public EndState(Elevator ele) {
		super(ele);
	}

	@Override
	public void moveDoor() {
		try {
			System.out.println("Closing doors.");
			Thread.sleep(CloseDoorTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void moveElevator() {
		try {
			System.out.println("Elevator completed trips.");
			Thread.sleep(CloseDoorTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}

