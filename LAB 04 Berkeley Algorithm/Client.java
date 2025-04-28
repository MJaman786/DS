import java.net.Socket;
import java.time.LocalTime;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5000);

        LocalTime currTime = LocalTime.now();
        String time = String.format("%02d:%02d:%02d", currTime.getHour(), currTime.getMinute(), currTime.getSecond());

        System.out.println(" - Before Synchronization: " + time);

        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        output.writeUTF(time);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        String syncTime = input.readUTF();

        System.out.println("   After Synchronization: " + syncTime);

        socket.close();
    }
}
