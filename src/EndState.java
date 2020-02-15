/**
 * EndState class is the final state of the elevator, when there is no
 * more movement of the doors or between floors 
 *
 */
public class EndState extends State {

<<<<<<< HEAD
<<<<<<< HEAD
public class EndState extends State {

=======
>>>>>>> bf41a8a... Updated end state
	public EndState(Elevator ele) {
		super(ele);
		// TODO Auto-generated constructor stub
	}
<<<<<<< HEAD

	@Override
	public void moveDoor() {
		// TODO Auto-generated method stub
		System.out.println("All requests processed!");
	}

	@Override
	public void moveElevator() {
		// TODO Auto-generated method stub
		
	}
=======
public class EndState {
>>>>>>> a62a93a... Updated endstate
=======
>>>>>>> bf41a8a... Updated end state

	@Override
	public void moveDoor() {
		// TODO Auto-generated method stub
		System.out.println("Do not move door.");
		
	}

	@Override
	public void moveElevator() {
		System.out.println("Do not move elevator.");
		
	}

}