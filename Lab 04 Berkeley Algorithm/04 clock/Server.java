import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

public class Server{
	public static void main(String[] args)throws Exception{
		ServerSocket server = new ServerSocket(5000);
		System.out.println("Server is running on port 5000");
		int n = 4;
		String[] clientTime = new String[n];
		Socket[] client = new Socket[n];
		int[] clientSecond = new int[n];
		
		LocalTime serverTime = LocalTime.now().withNano(0);
		System.out.println(" - Server time: "+serverTime);
		
		int serverSecond = serverTime.toSecondOfDay(); 
		
		for(int i=0; i<n; i++){
			client[i] = server.accept();
			DataInputStream input = new DataInputStream(client[i].getInputStream());
			clientTime[i] = input.readUTF();
			System.out.println("Client ("+(i+1)+") connected with time: "+clientTime[i]);
			clientSecond[i] = LocalTime.parse(clientTime[i]).toSecondOfDay();
		}
		
		int totalSeconds = serverSecond;
		for(int i=0; i<n; i++){
			totalSeconds += clientSecond[i];
		}
		
		int avgSeconds = totalSeconds / (n+1);
		for(int i=0; i<n; i++){
			int offset = avgSeconds - clientSecond[i];
			DataOutputStream output = new DataOutputStream(client[i].getOutputStream());
			output.writeInt(offset);
		}
		
		int serverOffset = avgSeconds - serverSecond;
		LocalTime newServerTime = LocalTime.ofSecondOfDay(serverSecond + serverOffset);
		System.out.println(" + Server Synchronized time "+newServerTime+" by offset: "+serverOffset);
	}
}
