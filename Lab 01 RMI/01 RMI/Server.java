import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server{
	public static void main(String[] args)throws Exception{
		LocateRegistry.createRegistry(5000);
		Adder stub = new AdderInterface();
		Naming.rebind("rmi://localhost:5000/Adder", stub);
		System.out.println("Server is running on port 5000");
	}
}
