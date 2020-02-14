
public class MoveState extends State {

	public MoveState(Elevator ele) {
		super(ele);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void moveDoor() {
		// TODO Auto-generated method stub
		System.out.println("Doors are closed while the elevator is moving");
	}

	@Override
	public void moveElevator() {
		// TODO Auto-generated method stub
		elevator.setElevatorMoving();
		System.out.println("Elevator state: Moving");
		
		try {
			Thread.sleep(FloorToFloorTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
