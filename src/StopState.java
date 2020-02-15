/**
 * MoveState class describes the state of the elevator and doors in motion
 * either of the doors or between floors.
 * @version Iteration 2: Feb 14th 2020 
 *
 */
public class StopState extends State {

	public StopState(Elevator ele) {
		super(ele);
	}

	@Override
	public void moveDoor() {
		// TODO Auto-generated method stub
		
		try {
			System.out.println("Doors are closing");
			Thread.sleep(CloseDoorTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void moveElevator() {
		// TODO Auto-generated method stub
		System.out.println("Moving elevator to floor " + elevator.getFloor());
		elevator.setState(new MoveState(elevator));
		try {
			Thread.sleep(FloorToFloorTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
