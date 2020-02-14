
public abstract class State {
	
	// use our values we calculated for the iteration 0
	protected static final long FloorToFloorTime = 3000;
	protected static final long OpenDoorTime = 1350;
	protected static final long HoldDoorTime = 5000;
	protected static final long CloseDoorTime = 1650;
    protected Elevator elevator;

    public State(Elevator ele) {
        this.elevator = ele;
    }

    public abstract void moveDoor();
    public abstract void moveElevator();

}
