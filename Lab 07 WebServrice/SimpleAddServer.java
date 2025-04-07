// Importing only the required classes
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class SimpleAddServer {
    public static void main(String[] args) throws Exception {
        // Create a server socket that listens on port 5000
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server started at http://localhost:5000");

        while (true) {
            // Accept the incoming connection from a browser (client)
            Socket socket = serverSocket.accept();

            // Get input stream to read the request sent by browser
            InputStream input = socket.getInputStream();
            byte[] buffer = new byte[1024]; // buffer to hold request data
            int bytesRead = input.read(buffer); // read the data into buffer

            // Convert bytes to string to read the HTTP request
            String request = new String(buffer, 0, bytesRead);
            System.out.println("Request:\n" + request);

            int sum = 0; // default sum

            // Check if the request has parameters like ?a=5&b=10
            if (request.startsWith("GET /?")) {
                // Extract the part between "/?" and "HTTP"
                int start = request.indexOf("/?") + 2;
                int end = request.indexOf(" HTTP");
                String query = request.substring(start, end); // a=5&b=10

                // Split the query into a and b
                String[] parts = query.split("&");
                int a = Integer.parseInt(parts[0].split("=")[1]); // get value of a
                int b = Integer.parseInt(parts[1].split("=")[1]); // get value of b
                sum = a + b; // calculate sum
            }

            // Create HTML response to send back
            String html = "<html><body><h2>Sum is: " + sum + "</h2></body></html>";
            String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n" + html;

            // Send the response to the browser
            OutputStream output = socket.getOutputStream();
            output.write(response.getBytes());

            // Close the socket
            socket.close();
        }
    }
}
