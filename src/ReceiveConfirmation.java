import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveConfirmation implements Runnable{

	
	DatagramPacket sendPacket, receivePacket;
	DatagramSocket Socket;
	int currentFloor,targetFloor, userChoice ;
    String time, direction;
    
	  /*
     * constructor 
     */
	public ReceiveConfirmation() {
		
	    
	    time = "";
		currentFloor = 0;
		targetFloor = 0;
		userChoice = 0;
		
		try {
			Socket = new DatagramSocket(4000);			
		}catch(SocketException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		byte[] data = new byte[100];
		receivePacket = new DatagramPacket(data,data.length);
		
		try {
			System.out.println("waiting for confirmation...");
			Socket.receive(receivePacket);
		}catch(IOException e) {
			System.out.println("waiting time out");
			e.printStackTrace();
			System.exit(1);
		}
		
		System.out.println("Packet received:");
		System.out.println("From " + receivePacket.getAddress()+ ", post: " + receivePacket.getPort());
		System.out.println("Contains: " + new String(receivePacket.getData()));
		System.out.println(System.nanoTime());
	}

}
