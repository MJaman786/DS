import java.rmi.Naming;
import java.util.Scanner;

public class Client{
	public static void main(String[] args)throws Exception{
		Scanner sc = new Scanner(System.in);
		Adder stub = (Adder) Naming.lookup("rmi://localhost:5000/Adder");
		System.out.print("Enter num1 : ");
		int num1 = sc.nextInt();
		System.out.print("Enter num2 : ");
		int num2 = sc.nextInt();
		int result = stub.add(num1, num2);
		System.out.println("Total : "+result);
	}
}
