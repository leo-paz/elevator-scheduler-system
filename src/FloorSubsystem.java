import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FloorSubsystem implements Runnable {
	
	private static String inputFile = "inputs/inputFile.txt";
	
	Scheduler s;
	
	public FloorSubsystem(Scheduler s) {
		this.s = s;
	}

	private static Direction getDirection(String s) {
		switch (s.toLowerCase()) {
		case "up":
			return Direction.UP;
		case "down":
			return Direction.DOWN;
		default:
			return null;
		}
	}
	
	private static List<FloorButtonRequest> readInputFile() {
		FileReader input = null;
		List<FloorButtonRequest> requests = new ArrayList<FloorButtonRequest>();
		
		try {
			input = new FileReader(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BufferedReader bufferRead = new BufferedReader(input);
		String myLine = null;

		try {
			while ((myLine = bufferRead.readLine()) != null) {
				String[] info = myLine.split(" ");

				String time = info[0];
				String floorNum = info[1];
				Direction direction = getDirection(info[2]);
				String destinationFloor = info[3];

				FloorButtonRequest currRequest = new FloorButtonRequest(time, floorNum, direction, destinationFloor);
				requests.add(currRequest);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return requests;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			List<FloorButtonRequest> requests = readInputFile();
			ArrayList<FloorButtonRequest> r = (ArrayList<FloorButtonRequest>) requests;
			for(int i = 0; i < r.size(); i++)
			{
				//let the user know on the console that the thread is running
				System.out.println(Thread.currentThread().getName() + " " + r.get(i).getFloorNum() +  " Requested an elevator ");
				s.scheduleElevator(r.get(i));
			}
	      			
	        	
		}
	}
}
