import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Server{
	public static void main(String[] args) throws Exception{
		ServerSocket server = new ServerSocket(5000);
		System.out.println("Server is running on port: 5000");
		
		String[] clientTime = new String[3];
		Socket[] client = new Socket[3];
		
		for(int i=0; i<3; i++){
			client[i] = server.accept();
			DataInputStream input = new DataInputStream(client[i].getInputStream());
			clientTime[i] = input.readUTF(); 
			System.out.println(" - Client "+(i+1)+" connected\n  Before Synchronization: "+clientTime[i]);
		}
		
		int totalSeconds = 0;
		for(int i=0; i<3; i++){
			LocalTime timeObj = LocalTime.parse(clientTime[i]);
			int seconds = timeObj.getHour() * 3600 + timeObj.getMinute() * 60 + timeObj.getSecond();
			totalSeconds += seconds;
		}
		
		int avgSeconds = totalSeconds / 3;
		int avgHour = avgSeconds / 3600;
		int avgMinu = (avgSeconds % 3600) / 60;
		int avgSec = avgSeconds % 60;
		
		String syncTime = String.format("%02d:%02d:%02d", avgHour, avgMinu, avgSec);
		System.out.println("+ Synchronized time send to all clients: "+syncTime);

		for(int i=0; i<3; i++){
			DataOutputStream output = new DataOutputStream(client[i].getOutputStream());
			output.writeUTF(syncTime); 
		}
		server.close();
	}
}

/*
+---------------------------------------------------------+
|                  Server (Coordinator)                   |
|                                                         |
|  1. Waits for connections from 3 clients                |
|  2. Collects their local times (HH:MM:SS)               |
|  3. Calculates average time (in seconds)                |
|  4. Sends synchronized time to all clients              |
+---------------------------------------------------------+
              ↓           ↓           ↓
        +----------+ +----------+ +----------+
        | Client 1 | | Client 2 | | Client 3 |
        +----------+ +----------+ +----------+
          05:34:41     05:34:51     05:35:06   
          (Before)     (Before)     (Before)

                    ↓ (Server averages)

             🕑 New Time: 05:34:52

          05:34:52     05:34:52     05:34:52   
           (After)      (After)      (After)

*/
