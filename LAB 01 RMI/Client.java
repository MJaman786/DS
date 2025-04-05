import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) {
        try {
            // Lookup the remote object from RMI registry
            Adder stub = (Adder) Naming.lookup("rmi://localhost:5000/Adder");

            // Take input from the user
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter first number: ");
            int a = sc.nextInt();
            System.out.print("Enter second number: ");
            int b = sc.nextInt();

            sc.close();
            // Call the remote method
            int result = stub.add(a, b);
            System.out.println("Result: " + result);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
