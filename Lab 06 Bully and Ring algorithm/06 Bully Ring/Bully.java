import java.util.Scanner;
import java.util.Arrays;

public class Bully{
	static int num = 7, coordinator = 7;
	static boolean[] alive = new boolean[num+1];
	
	public static void election(int starter){
		for(int i=starter+1; i<num; i++){
			if(alive[i]){
				System.out.println("\n "+starter+" sends election request to processID: "+i);
				System.out.println(" "+i+" sends ok response to processID: "+starter);
			}
		}
		for(int i=starter+1; i<num; i++){
			if(alive[i]){
				election(i);
			}
		}
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Arrays.fill(alive, true);
		System.out.println("Total number of process are 1-7");
		System.out.println("Iniial Coordinator: "+coordinator);
		
		System.out.print(" - Enter crashed processID: ");
		int crashed = sc.nextInt();
		alive[crashed] = false;
		
		if(crashed==coordinator){
			System.out.print(" - Enter starter processID: ");
			int starter = sc.nextInt();
			
			election(starter);
			
			for(int i=num; i>0; i--){
				if(alive[i]){
					coordinator = i;
					System.out.println("\nNew Coordinator is elected: "+i);
					break;
				}
			}
			
			for(int i=0; i<num; i++){
				if(alive[i] && i!= coordinator){
					System.out.println("Coordinator "+coordinator+" updates msg to processID: "+i);
				}
			}
			
		}else{
			System.out.println("Coordinator is alive no need for election");
		}
	}
}

/*
vboxuser@Ubuntu:~/Desktop/practice/06 Bully Ring$ java Bully
Total number of process are 1-7
Iniial Coordinator: 7
 - Enter crashed processID: 7
 - Enter starter processID: 4

 4 sends election request to processID: 5
 5 sends ok response to processID: 4

 4 sends election request to processID: 6
 6 sends ok response to processID: 4

 5 sends election request to processID: 6
 6 sends ok response to processID: 5

New Coordinator is elected: 6
Coordinator 6 updates msg to processID: 0
Coordinator 6 updates msg to processID: 1
Coordinator 6 updates msg to processID: 2
Coordinator 6 updates msg to processID: 3
Coordinator 6 updates msg to processID: 4
Coordinator 6 updates msg to processID: 5

*/
