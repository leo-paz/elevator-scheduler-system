/**
 * This is the Scheduler class that implements runnable. This class schedules communication between 
 * the elevator and the user requests using datagram packets. It sends a message to the elevator as
 * soon as user requests(floor subsystem) an elevator and choose its desired floor.The elevator 
 * receives these requests through the scheduler. 
 * coordinates with the floor buttons.
 * @version Iteration 3: March 7th 2020 
 * 
 */

import java.io.IOException;
import java.net.*;
import java.util.*;

public class Scheduler implements Runnable {
	
	DatagramPacket receiveFloorPacket, sendFloorPacket, sendElevatorPacket, receiveElevatorPacket;
    DatagramSocket receiveFloorSocket, sendFloorSocket, sendElevatorSocket, receiveElevatorSocket;
    
	String[] PackageInfo = new String[5];
	private int[] portNumber = new int[4];
	DatagramSocket[] elevatorSockets;
	Elevator[] elevators;
	private int floorSubsystemPort;
	private int sendElevatorPort;
	
	private int chosenElevator;
	
	/*
	 * constructor 
	 */
	public Scheduler() {
		elevators = new Elevator[4];
		elevatorSockets = new DatagramSocket[4];
		
		for(int i = 0; i < this.elevators.length; i++) {
			this.portNumber[i] = 2000 + i; 
			this.elevators[i] = new Elevator(1, i, portNumber[i]);
			try {
				this.elevatorSockets[i] = new DatagramSocket();//portNumber[i]);
				
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		try {
			sendFloorSocket = new DatagramSocket();
            sendElevatorSocket = new DatagramSocket();
            
            receiveFloorSocket = new DatagramSocket(23);
            receiveElevatorSocket = new DatagramSocket(69);
            
        } catch(SocketException se){
            se.printStackTrace();
            System.exit(1);
        }
		
	}
	
	/* 
	 * receivePacket function receives a packet and returns a specific message 
	 */
	public void receiveFloorSubsystem() {
		byte[] data = new byte[100];
		receiveFloorPacket = new DatagramPacket(data, data.length);
		
		try {
			System.out.println("Scheduler waiting for data from Floor Subsystem");
			receiveFloorSocket.receive(receiveFloorPacket);
			floorSubsystemPort = receiveFloorPacket.getPort();
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		byte receivedData[] = new byte[receiveFloorPacket.getLength()];
		System.arraycopy(data, 0, receivedData, 0, receivedData.length);
		
		System.out.println("Scheduler receiving package from FloorSubsystem: ");
		System.out.println("From " + receiveFloorPacket.getAddress()+ ", port: " + receiveFloorPacket.getPort());
		
		String receivedString = new String(receiveFloorPacket.getData()).trim();
		
		System.out.println("Contains: " + receivedString);
		
		this.PackageInfo = receivedString.split("\t");
		
		System.out.println(PackageInfo[1] + PackageInfo[2] + PackageInfo[3] + PackageInfo[0]);
		
		// Logic to determine which elevator goes where
		int minimumDistance = Integer.MAX_VALUE;
		int distance = 0;
		int currentChosenElevator = 0;
		String s = "";
		for(int i = 0; i < this.elevators.length; i++) {
			distance =  Integer.parseInt(PackageInfo[1]) - elevators[i].getFloor();
			if( Math.abs(distance) < Math.abs(minimumDistance)) {
				minimumDistance = distance;
				currentChosenElevator = i;
			}
		}
		
		setChosenElevator(currentChosenElevator);
		
	
		if(minimumDistance > 0) {
			s = "Up" + "," + PackageInfo[1] + "," + PackageInfo[2] + "," +  PackageInfo[3] + "," + PackageInfo[4];
		} else if(minimumDistance < 0) {
			s = "Down" + "," + PackageInfo[1] + "," + PackageInfo[2] + "," +  PackageInfo[3] + "," + PackageInfo[4];
		} else {
			s = "Stop" + "," + PackageInfo[1] + "," + PackageInfo[2] + "," +  PackageInfo[3] + "," + PackageInfo[4];
		}

		
		byte[] msg = s.getBytes();
		sendElevatorPort = portNumber[chosenElevator] - 1000;
		
		sendToElevatorSubsystem(currentChosenElevator, msg);
	}
	
	public synchronized void setChosenElevator(int i) {
		chosenElevator = i;
	}
	private synchronized int getChosenElevator() {
		return chosenElevator;
	}
	public void sendToElevatorSubsystem(int chosenElevator, byte[] msg) {
		try {
			sendElevatorPacket = new DatagramPacket(msg,msg.length,InetAddress.getLocalHost(), sendElevatorPort);
		} catch(UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		}
		int f = Integer.parseInt(PackageInfo[1]);
		//System.out.println("Floor: " + elevators[chosenElevator].getFloor() + " Num: " + elevators[chosenElevator].getElevatorNum() + " Port: " + elevators[chosenElevator].getElevatorPortNum() );
		elevators[chosenElevator].setCurrentFloor(f);
		System.out.println("Scheduler sending package to ElevatorSubsystem ");
		System.out.println("To " + sendElevatorPacket.getAddress() + ", port: " + sendElevatorPacket.getPort());
		System.out.println("Package contains: " + new String(sendElevatorPacket.getData()));
		
		try {
			elevatorSockets[chosenElevator].send(sendElevatorPacket);
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		System.out.println("Message sent to elevator \n");
		
	}
	public void receiveElevatorSubsystem() {
		byte[] getReply = new byte[100];
		receiveElevatorPacket = new DatagramPacket(getReply,getReply.length);
		
		try {
			elevatorSockets[chosenElevator].receive(receiveElevatorPacket);
			}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
			
		
		System.out.println("Package received from ElevatorSubSystem...\n");
		System.out.println("from " + receiveElevatorPacket.getAddress() + " , port: " + receiveElevatorPacket.getPort());
		System.out.println("package contains: " + new String(receiveElevatorPacket.getData()));
		elevators[chosenElevator].setCurrentFloor(Integer.parseInt(PackageInfo[3]));
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
	
		sendToFloorSubsystem(getReply);
	}
	public void sendToFloorSubsystem(byte[] getReply) {
		sendFloorPacket = new DatagramPacket(getReply, receiveElevatorPacket.getLength(), receiveElevatorPacket.getAddress(), 4000);
		
		System.out.println("Package sending to FloorSubSytem");
		System.out.println("To " + receiveElevatorPacket.getAddress() + " , host:" + receiveElevatorPacket.getPort());
		System.out.println("Package contains: " + new String(receiveElevatorPacket.getData()));
		
		try {
			sendFloorSocket.send(sendFloorPacket);
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		System.out.println("Package sent to Floor Subsystem");
	}
	
	public static void main(String[] args) {
		Scheduler scheduler = new Scheduler();
		Thread thread = new Thread(scheduler);
		thread.start();
	}

	/**
	 * Create two threads, one for dealing with the client and one for dealing with the server
	 */
	public void run() {
		 Thread client = new Thread("FloorSubsystem Thread"){
	            public void run(){
	            	for(int i = 0; i<10; i++)
	            	{
	            		receiveFloorSubsystem();
	            	}
	            		
	            }
	        };
	        client.start();
		
	        Thread server = new Thread("ElevatorSubsystem Thread"){
	            public void run(){
	            	for(int i = 0; i<10; i++)
	            	{
	            		receiveElevatorSubsystem();
	            	}
	          }
	        };
	        server.start();		
	}
}
