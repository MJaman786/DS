import java.util.Scanner;

public class TokenRing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes in a ring: ");
        int num = sc.nextInt();

        System.out.println("Ring formed is: ");
        for (int i = 0; i < num; i++) {
            System.out.print(i + " -> ");
        }
        System.out.println("0");

        int choice = 0;
        do {
            System.out.print("Enter sender: ");
            int sender = sc.nextInt();

            System.out.print("Enter receiver: ");
            int receiver = sc.nextInt();

            System.out.print("Enter data: ");
            int data = sc.nextInt();

            int token = 0;
            System.out.print("Token passing: ");
            for (int i = token; i < sender; i++) {
                System.out.print(i + " -> ");
            }
            System.out.println(sender);
            System.out.println("Sender " + sender + " sending data: " + data);

            for (int i = sender+1; i != receiver; i = (i + 1) % num) {
                System.out.println("Data: " + data + " forwarded -> " + i);
            }
            System.out.println("Data: " + data + " received by Receiver '" + receiver + "'");
            token = sender;
            System.out.println("Now the token is with: "+sender);

            System.out.println("do you wnat to enter data again if yes then '0' or No then '1' ");
            choice = sc.nextInt();

        } while (choice == 1);

        sc.close();
    }
}