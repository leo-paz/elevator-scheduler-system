import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * This is the Elevator class. It get elevator gets the requests from the floors to be serviced
 * @version Iteration 3: March 7th, 2020
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
	String name;
	
	/**
	 * This is the constructor
	 * @param s is a type Scheduler
	 */
	public int getElevatorNum()
	{
		return elevatorNumber;
	}
	public int getElevatorPortNum()
	{
		return portNumber;
	}

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
	
	public Elevator(int po,String n) {
		try {
			portNumber = po;

			receiveSocket = new DatagramSocket(portNumber);

		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);
		}
		this.name = n;
	}
	
	public void setCurrentFloor(int i)
	{
		currentFloor = i; 
		//currentState.moveDoor();
		//currentState.moveElevator();
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
	public void sendMsg(byte[] data, int len, InetAddress address, DatagramPacket packet, DatagramSocket sendReceiveSocket, int port) throws IOException {

		

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
	
	/**
	 * Elevator is going to the floor to pick the passenger up
	 */
	public void pickUpPassenger()
	{

		System.out.println("Elevator is going to floor "  );

	}
	
	
	/**
	 * The passenger has requested a floor inside the elevator
	 */
	public void deliverPassenger()
	{
		System.out.println("The elevator has received a request from a floor and is delivering the passenger");

	}
	
	/*
	 * run method keep this thread to check if the received message is valid, if valid,
	 * check the status of floor and send message packet. Else, throw IllegalAccessException
	 */
	@Override
	public void run() {
		while (true) {
			byte data[];
			boolean valid;
			ArrayList<String> arrayList;

			data = receiveMsg(receiveSocket);
			
			DatagramSocket newSocket;
						
			data = fixByteArrLength(receivePacket.getLength(), data);
			System.out.println("Elevator has received a request");
			String msg = new String(data);
				
			try {
				newSocket = new DatagramSocket();
				DatagramPacket sendPacket = null;
				
				String s = "Done";
				byte[] result = s.getBytes();
				sendMsg(result, result.length, receivePacket.getAddress(), sendPacket, newSocket, receivePacket.getPort());
				System.out.println("PortNumber: "+receivePacket.getPort());
				newSocket.close();
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			this.pickUpPassenger();
			this.deliverPassenger();
		}


	
		
	}
	
}
