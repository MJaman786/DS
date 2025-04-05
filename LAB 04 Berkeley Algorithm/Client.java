import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.time.LocalTime;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5000); // Connect to server

        // Step 1: Get current time in hh:mm format
        LocalTime currentTime = LocalTime.now();
        String time = String.format("%02d:%02d", currentTime.getHour(), currentTime.getMinute());
        System.out.println("Local Time: " + time);

        // Step 2: Send current time to server
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        output.writeUTF(time);

        // Step 3: Receive synchronized time from server
        DataInputStream input = new DataInputStream(socket.getInputStream());
        String syncedTime = input.readUTF();
        System.out.println("Synchronized Time: " + syncedTime);

        socket.close(); // Close connection
    }
}
