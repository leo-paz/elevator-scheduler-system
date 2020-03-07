import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.*;
/**
 * This is the FloorSubsystem class that gets the direction of the elevator 
 * traveling to, time it will take and the destination floor.
 * @version Iteration 3: March 7, 2020
 *
 */
public class FloorSubsystem implements Runnable {
	
	private static String inputFile = "inputs/inputFile.txt";
	
	DatagramPacket sendPacket, receivePacket;
	DatagramSocket socket;
	
	//Scheduler s;
	
	/**
	 * This is the constructor
	 * @param s is a type scheduler
	 */
	public FloorSubsystem() {

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
	
	@Override
	public void run() {
		//required as implements runnable
		
	}

	
	public static void main(String args[]) {
		Thread sThread = new Thread(new SendRequest());
		Thread rThread = new Thread(new ReceiveConfirmation());
		
		sThread.start();
		rThread.start();
		
	
	}
}
