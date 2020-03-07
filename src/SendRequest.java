import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
/**
 * This is the SendRequest Class that sends the request to the scheduler. The elevator direction
 * button, the user input floor all are sent though this class.
 * @version Iteration 3: March 7, 2020
 *
 */
public class SendRequest implements Runnable{
	DatagramPacket sendPacket, receivePacket;
	DatagramSocket Socket;
	int userChoice;
	
	private static String inputFile = "inputs/inputFile.txt";
    
	int currentFloor,targetFloor ;
    String time, error;
    Direction direction;
    
    /*
     * The is the send request's constructor
     */
	public SendRequest() {
		
		time = "";
		currentFloor = 0;
		targetFloor = 0;
		userChoice = 0;
		direction = direction.UP;
		
		try {
			Socket = new DatagramSocket();			
		}catch(SocketException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	/**
	 * This is getter method for the direction of the elevator travel
	 * @param s is a type string 
	 * @return the direction at which the elevator is traveling
	 */
	public static Direction getDirection(String s) {
		switch (s.toLowerCase()) {
		case "up":
			return Direction.UP;
		case "down":
			return Direction.DOWN;
		default:
			return null;
		}
	}
	
	/**
	 * This is the parser method that allows to take the input from the 
	 * text file from all the data generator from iteration 0
	 * @return a list of all the requests made by gathering the information from the 
	 * text file 
	 */
	public static List<FloorButtonRequest> readInputFile() {
		FileReader input = null;
		List<FloorButtonRequest> requests = new ArrayList<FloorButtonRequest>();
		File f = new File(inputFile);
		
		try {
			input = new FileReader(f.getAbsolutePath()); //file containing all the data captured
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BufferedReader bufferRead = new BufferedReader(input);
		String myLine = null;

		try {
			while ((myLine = bufferRead.readLine()) != null) {
				String[] info = myLine.split(" "); // the column are split by spaces in the etxt file

				// all the columns in the text with their indexes
				String time = info[0];
				String floorNum = info[1];
				Direction direction = getDirection(info[2]);
				String destinationFloor = info[3];
				
				FloorButtonRequest currRequest = new FloorButtonRequest(time, floorNum, direction, destinationFloor, false);
				requests.add(currRequest); // add the request to the list
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return requests;
	}
	
	/*
	 * set the time 
	 * @param a : a tring of time  
	 */
	public void setTime(String a) {
		time = a;
	}
	public String getTime() {
		return time;
	}
	/*
	 * set the target floor
	 * @param floor : a integer of target floor
	 */
	public void setTargetFloor(int floor) {
		targetFloor = floor;
	}
	
	/*
	 * set the floor number 
	 * @param floor : a integer of current floor
	 */
	public void setFloor(int floor) {
		currentFloor = floor;
	}
	
	/*
	 * set the direction 
	 * @param direction : a string of direction 
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/*
	 * set error 
	 * @error : a kind of error that gonna happen
	 */
	public void setError(String error) {
		this.error = error;
	}
	
	@Override
	/*
	 * the thread continuously send message to target port
	 */
	public void run() {
		
		List<FloorButtonRequest> list = readInputFile();
		
		int acurrent , atarget;
		String time = "0", error = "null";
		Direction direction;
		
		
		for(int i =0; i<list.size();i++) {
			FloorButtonRequest r = list.get(i);
			
			
			this.setTime(r.getTime());
			time = r.getTime();
			this.setFloor(Integer.parseInt(r.getFloorNum()));
			acurrent = Integer.parseInt(r.getFloorNum());
			this.setTargetFloor(Integer.parseInt(r.getDestinationFloor()));
			atarget = Integer.parseInt(r.getDestinationFloor());
			this.setDirection(r.getDirection());
			direction = r.getDirection();
			//this.setError(error);
		
		String a = time + "\t" + currentFloor + "\t" + direction.toString() + "\t" + targetFloor + "\t" + error;
		
		byte[] msg = a.getBytes();
		 
		try {
			sendPacket = new DatagramPacket(msg,msg.length, InetAddress.getLocalHost(),23);
			
		}catch(UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		System.out.println("Client sending...");
		System.out.println("To " + sendPacket.getAddress() + ", post: " + sendPacket.getPort());
		System.out.println("Contains: " + new String(sendPacket.getData()));
		
		try {
			Socket.send(sendPacket);
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		System.out.println("Message send");
		
		}
		
	}
}
