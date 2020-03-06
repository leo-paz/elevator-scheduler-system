import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * This is the Elevator class. It get elevator gets the requests from the floors to be serviced
 * @version Iteration 2: Feb 15th 2020
 *
 */
public class Elevator implements Runnable {
	//Scheduler s;
	State currentState; //holds the current state of the elevator
	boolean isElevatorMoving = false;
	boolean isDoorOpen = false;
	int currentFloor;
	int elevatorNumber;
	int portNumber;
	private DatagramSocket receiveSocket;
	private DatagramPacket receivePacket;
	DatagramPacket sendPacket;
	DatagramSocket sendReceiveSocket;
	ArrayList<Integer> floorsToService = new ArrayList<Integer>();
	
	/**
	 * This is the constructor
	 * @param s is a type Scheduler
	 */
	public Elevator(int currentFloor, int elevatorNumber, int portNumber) {
		this.currentFloor = currentFloor;
		this.elevatorNumber = elevatorNumber;
		this.portNumber = portNumber;
		
		try {
			receiveSocket = new DatagramSocket(portNumber);
		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);
		}		
	}
	
	public void setCurrentFloor(int i)
	{
		currentFloor = i; 
		currentState.moveDoor();
		currentState.moveElevator();
	}
	
	/**
	 * This is to set the currentFloor the elevator is on
	 * @param floor The floor to set the current floor too
	 */
	public void setFloor(int floor) {
		currentFloor = floor;
		currentState.moveDoor();
		currentState.moveElevator();
	}
	
	/**
	 * Returns current floor the elevator is on
	 */
	public int getFloor() {
		return currentFloor;
	}
	
	/**
	 * This is called when we arrive at a new floor
	 */
	public void arriveAtFloor() {
		currentState.moveDoor();
		currentState.moveElevator();
	}
	
	/**
	 * This is called to set a new state (moving, stopped, end)
	 * @param state
	 */
	public void setState(State state) {
		this.currentState = state;
	}
	
	/**
	 * When the elevator is moving, we set these boolean values
	 */
	public void setElevatorMoving()
	{
		isElevatorMoving = true;
		isDoorOpen = false;
	}
	
	/**
	 *  When elevator is stopped, we set these boolean values
	 */
	public void setElevatorStopped()
	{
		isElevatorMoving = false;
		isDoorOpen = true;
	}
	
	/*
	 * receive a packet which contains a message 
	 */
	public byte[] receiveMsg(DatagramSocket receiveSocket) {
		byte data[] = new byte[30];
		receivePacket = new DatagramPacket(data, data.length);
		System.out.println("--------------------------------");
		System.out.println(Thread.currentThread().getName() + ": Waiting for Packet.");
		try {

			receiveSocket.receive(receivePacket);
		} catch (IOException e) {
			System.out.println("IO Exception: likely: " + "Receive Socket Timed Out.\n" + e);
			e.printStackTrace();
			System.exit(1);
		}
		data = fixByteArrLength(receivePacket.getLength(), data);
		System.out.println(Thread.currentThread().getName() + ": Packet received:");
		System.out.println("From host: " + receivePacket.getAddress());
		System.out.println("Host port: " + receivePacket.getPort());
		int len = receivePacket.getLength();

		System.out.println("Containing: " + new String(data, 0, len));
		return data;
	}
	
	public byte[] fixByteArrLength(int len, byte[] arr) {
		byte newdata[] = new byte[len];
		System.arraycopy(arr, 0, newdata, 0, newdata.length);
		return newdata;

	}
	
	/*
	 * send a packet which contains a message 
	 */
	public void sendMsg(byte[] data, int len, InetAddress address, int port) throws IOException {

		System.out.println("--------------------------------");
		System.out.println(Thread.currentThread().getName() + ": sending a packet containing(String): " + new String(data));
		try {
			sendPacket = new DatagramPacket(data, len, address, port);
			sendReceiveSocket.send(sendPacket);
			System.out.println("Send Successfully");
		} catch (UnknownHostException e) {
			System.out.println("IO Exception: likely: " + "Send Socket Timed Out.\n" + e);
			e.printStackTrace();
			System.exit(1);
		}

	}
	
	
	public void pickUpPassenger()
	{
		byte [] data = receiveMsg(receiveSocket);
		int destFloor = data[1];
		
		//byte array should contain 0 - REQUESTED FLOOR 1 - Destination floor.
		floorsToService.add(destFloor);
		
		currentState.moveElevator();
		currentState.moveDoor();
		
		System.out.println("The elevator has received a request from a floor and is going to the floor");
		arriveAtFloor();
		//state = moving
		// string = going to floor 
		//when the data is received, go to the floor and pick up the passenger.
	}
	
	public void deliverPassenger()
	{
		byte[] data = new byte[floorsToService.size()];
		
		for(int i = 0; i < floorsToService.size(); i++)
		{
			data[i] = (byte) ((int) floorsToService.get(i));
		}
		
		DatagramPacket sendPacket = new DatagramPacket(data,1);
		DatagramSocket sendReceiveSocket;
		
		try
		{
			sendMsg(data, data.length, InetAddress.getLocalHost(), 25 );
		}
		catch(IOException e)
		{
			System.out.print("Exception");
			System.exit(1);
		}
		
		arriveAtFloor();
		
		//when the passenger is picked up, go to the desired floor and deliver the passenger
	}

	public static void main(String args[])
	{
		//default, the scheduler 
		//Elevator e = new Elevator();
		//e.pickUpPassenger();
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		/*StopState ms = new StopState(this);
		setState(ms);
		
		while(true) {
			//FloorButtonRequest request = s.getElevator();//the destination floor the elevator is asked to get to
			System.out.println(Thread.currentThread().getName() + " has been requested and elevator goes to floor " + request.getFloorNum() + " for pickup.");
			
			// TODO: Add a set method for the rest of the elevator information so it can be passed to states
			setFloor(Integer.parseInt(request.getFloorNum()));
			arriveAtFloor();
			System.out.println("The passenger gets on the elevator at floor " + request.getFloorNum() + " and goes " + request.getDirection().toString() + " to Floor " + request.getDestinationFloor());
			// if we are done the requests move to end state
			if(request.isLastRequest()) {
				setState(new EndState(this));
				currentState.moveDoor();
				currentState.moveElevator();
			}
		}
		*/
		
	}
	
}
