import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
 * This is the FloorSubsystem class that gets the direction of the elevator 
 * traveling to, time it will take and the destination floor.
 * @version Iteration 1: Feb 1st 2020
 *
 */
public class FloorSubsystem implements Runnable {
	
	private static String inputFile = "inputs/inputFile.txt";
	
	Scheduler s;
	
	/**
	 * This is the constructor
	 * @param s is a type scheduler
	 */
	public FloorSubsystem(Scheduler s) {
		this.s = s;
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
		// TODO Auto-generated method stub
		ArrayList<FloorButtonRequest> requests = (ArrayList<FloorButtonRequest>) readInputFile();
			
		for(int i = 0; i < requests.size(); i++)
		{
			if (s.getCompletedRequests() >= requests.size()) break;
			//let the user know on the console that the thread is running
			System.out.println(Thread.currentThread().getName() + " " + requests.get(i).getFloorNum() +  " Requested an elevator ");
			// if last request set the last request flag
			if( i == requests.size() - 1) requests.get(i).setIsLastRequest();
			s.scheduleElevator(requests.get(i));
		}
		try {
			Thread.sleep(1000); // pre-cautionary so this function doesn't exit before a thread is done work
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}