import java.util.Arrays;
import java.util.Scanner;

public class BullyMain {
    static int n = 7, coordinator = 7;
    static boolean[] alive = new boolean[n + 1];

    public static void election(int starter) {
        // First, send election requests to all higher processes
        for (int i = starter + 1; i <= n; i++) {
            if (alive[i]) {
                System.out.println("process " + starter + " sends Election request -> " + i);
                System.out.println("process " + i + " sends ok response -> " + starter + "\n");
            }
        }

        // After sending all requests, now recursively call election for each higher process
        for (int i = starter + 1; i <= n; i++) {
            if (alive[i]) {
                election(i);
            }
        }
    }

    public static void main(String[] args) {
        Arrays.fill(alive, true);
        Scanner sc = new Scanner(System.in);

        System.out.println("Total Number of process (1 to 7)");
        System.out.println("Initial Coordinator: " + coordinator);

        System.out.print("Enter the crashed processID: ");
        int crashed = sc.nextInt();
        alive[crashed] = false;

        if (crashed == coordinator) {
            System.out.print("\nEnter the process who started election: ");
            int starter = sc.nextInt();

            // Start election
            election(starter);

            // Find new coordinator
            for (int i = n; i >= 1; i--) {
                if (alive[i]) {
                    coordinator = i;
                    break;
                }
            }

            System.out.println("\nNew Coordinator -> " + coordinator);

            // Coordinator informs everyone
            for (int i = 1; i <= n; i++) {
                if (alive[i] && i != coordinator) {
                    System.out.println("Coordinator " + coordinator + " update message to -> " + i);
                }
            }
        } else {
            System.out.println("Coordinator is alive, no need for Election.");
        }

        sc.close();
    }
}

/*
vboxuser@Ubuntu:~/Desktop/Bully$ java Bully
Total Number of process (1 to 7)
Initial Coordinator: 7
Enter the crashed processID: 7

Enter the process who started election: 4
process 4 sends Election request -> 5
process 5 sends ok response -> 4

process 4 sends Election request -> 6
process 6 sends ok response -> 4

process 5 sends Election request -> 6
process 6 sends ok response -> 5


New Coordinator -> 6
Coordinator 6 update message to -> 1
Coordinator 6 update message to -> 2
Coordinator 6 update message to -> 3
Coordinator 6 update message to -> 4
Coordinator 6 update message to -> 5
*/
