import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class AdderInterface extends UnicastRemoteObject implements Adder{
	AdderInterface()throws RemoteException{
		super();
	}
	public int add(int a, int b){
		return a + b;
	}
}
