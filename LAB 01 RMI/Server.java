import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String args[]) {
        try {
            // Start the RMI Registry on port 5000
            LocateRegistry.createRegistry(5000);

            // Create an instance of the remote object
            Adder stub = new AdderRemote();

            // Bind the remote object to the RMI registry with name "Adder"
            Naming.rebind("rmi://localhost:5000/Adder", stub);

            System.out.println("Server is ready.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
