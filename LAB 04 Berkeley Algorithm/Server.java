import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.time.LocalTime;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5000);
        System.out.println("Server started. Waiting for 3 clients...");

        String[] clientTimes = new String[3]; // To store client time strings
        Socket[] clients = new Socket[3];     // To store client sockets

        // Step 1: Accept connections and read time from clients
        for (int i = 0; i < 3; i++) {
            clients[i] = server.accept();
            DataInputStream input = new DataInputStream(clients[i].getInputStream());
            clientTimes[i] = input.readUTF(); // Receive time like "03:25"
            System.out.println("Client " + (i + 1) + " Time: " + clientTimes[i]);
        }

        // Step 2: Calculate average time
        int totalMinutes = 0;
        for (int i = 0; i < 3; i++) {
            LocalTime timeObj = LocalTime.parse(clientTimes[i]);
            int minutes = timeObj.getHour() * 60 + timeObj.getMinute();
            totalMinutes += minutes;
        }

        int avgMinutes = totalMinutes / 3;
        int avgHour = avgMinutes / 60;
        int avgMin = avgMinutes % 60;
        String avgTime = String.format("%02d:%02d", avgHour, avgMin);

        System.out.println("Synchronized Time: " + avgTime);

        // Step 3: Send synchronized time to all clients
        for (int i = 0; i < 3; i++) {
            DataOutputStream output = new DataOutputStream(clients[i].getOutputStream());
            output.writeUTF(avgTime); // Send average time
        }

        server.close();
    }
}
