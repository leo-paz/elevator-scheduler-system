import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

public class FloorSubsystem implements Runnable {
	
	private static String inputFile = "inputs/inputFile.txt";

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
		try {
			input = new FileReader(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BufferedReader bufferRead = new BufferedReader(input);
		String myLine = null;

		List<FloorButtonRequest> requests = new LinkedList<FloorButtonRequest>();

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
		
	}
	
		private static Date convertTime(String dateString) throws ParseException {
        DateFormat format = new SimpleDateFormat("hh:mm:ss.SSS", Locale.ENGLISH);
        try {
			return format.parse(dateString);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    public static void main(String[] args) {
        List<FloorButtonRequest> inputs = readInputFile();   
        
        Collections.sort(inputs, new Comparator<FloorButtonRequest>() {
            @Override
            public int compare(FloorButtonRequest request1, FloorButtonRequest request2) {
                Date time1 = new Date();
                Date time2 = new Date();
				try {
					time1 = convertTime(request1.getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				try {
					time2 = convertTime(request2.getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
                if (time1.after(time2))
                    return 1;
                else 
                    return 0;
            }
        });

    }
}
