import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class RingAlgo {
    // this is number of processes. And inintal leader is '7'
    static int nProcesses = 7, coordinator = 7;

    // this boolean type of array to track alive processes (1 - 7).
    static boolean[] alive = new boolean[nProcesses + 1];

    // this arraylist is to push the processes in circular order.
    static ArrayList<Integer> list = new ArrayList<Integer>();

    public static void main(String[] args) {

        // initalize array with all alive process.
        Arrays.fill(alive, true);

        System.out.println("There are total process: 1-7");
        System.out.println("Initial coordinator: " + coordinator);

        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter the crashed process: ");
        int crashed = sc.nextInt();

        alive[crashed] = false;

        if (crashed == coordinator) {

            System.out.print("Enter the starter processID: ");
            int starter = sc.nextInt();

            for (int i = 0; i < nProcesses; i++) {
                // ring
                int index = (starter + i) % nProcesses;
                if (index == 0) {
                    index = nProcesses;
                }
                if (alive[index] == true) {
                    list.add(index);
                    System.out.println("Election array: " + list);
                }
            }

            int leader = list.get(0); // assume first element is max initially

            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) > leader) {
                    leader = list.get(i); // update if a bigger element is found
                }
            }
            System.out.println("\nLeader elected: " + leader);

            for (int i = 1; i < nProcesses; i++) {
                if (alive[i] == true && i != leader) {
                    System.out.println("Leader " + leader + " updates to -> " + i);
                }
            }

        } else {
            System.out.println("Coordinator is Alive, no need for Election");
        }
        sc.close();
    }
}

/*
 * There are total process: 1-7
 * Initial coordinator: 7
 * 
 * Enter the crashed process: 7
 * Enter the starter processID: 4
 * Election array: [4]
 * Election array: [4, 5]
 * Election array: [4, 5, 6]
 * Election array: [4, 5, 6, 1]
 * Election array: [4, 5, 6, 1, 2]
 * Election array: [4, 5, 6, 1, 2, 3]
 * 
 * Leader elected: 6
 * Leader 6 updates to -> 1
 * Leader 6 updates to -> 2
 * Leader 6 updates to -> 3
 * Leader 6 updates to -> 4
 * Leader 6 updates to -> 5
 */