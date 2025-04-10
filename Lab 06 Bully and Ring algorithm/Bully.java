import java.util.Scanner;
import java.util.Arrays;

public class Bully{
    static int n = 7;
    static boolean[] alive = new boolean[n+1];
    static int coordinator = 7; 
    public static void main(String[] args){
        
        Arrays.fill(alive, true);
        Scanner sc = new Scanner(System.in);

        System.out.println("Processes: 1 to 7");
        System.out.println("Initaial Co-ordinator: "+coordinator);

        System.out.print("Enter crashed process ID (1 to 7): ");
        int crashed = sc.nextInt();

        alive[crashed]=false;
        System.out.println("Process "+crashed+" has crashed.");

        if (crashed==coordinator) {
            System.out.print("Enter the process ID that starts ELECTION: ");
            int starter = sc.nextInt();

            System.out.println("Election started by process "+starter);
            boolean gotOK = false;

            for (int i = starter+1; i <= n; i++) {
                if (alive[i]==true) {
                    System.out.println("process "+starter+ " sends ELECTION to process "+i);
                    System.out.println("process "+i+" replies OK msg to process "+starter);
                    gotOK = true;
                }
            }

            for (int i = n-1; i >= 1; i++) {
                if (alive[i]) {
                    coordinator = i;
                    break;
                }
            }

            System.out.println("process "+coordinator+" becomes the new CO-ORDINATOR");

            for (int i = 0; i < n; i++) {
                if (alive[i]==true && i!=coordinator) {
                    System.out.println("process "+coordinator+" announces CO-ORDINATOR to "+i);
                }
            }
            
        }
        else{
            System.out.println("Coordinator is still alive. No Election is needed.");
        }
        sc.close();
    }
}
/*
Initial Coordinator: 6
Enter crashed process ID: 6
Enter election starter process: 4
Process 4 sends ELECTION to 5
Process 5 replies OK to 4
Process 4 sends ELECTION to 6

Process 5 sends ELECTION to 6

Process 5 becomes the new COORDINATOR
Process 5 announces COORDINATOR to 0
Process 5 announces COORDINATOR to 1
Process 5 announces COORDINATOR to 2
Process 5 announces COORDINATOR to 3
Process 5 announces COORDINATOR to 4
 
*/