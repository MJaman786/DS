import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Ring{
	static int num = 7, coordinator = 7;
	static boolean[] alive = new boolean[num+1];
	static ArrayList<Integer> list = new ArrayList<Integer>();
		
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
			
			System.out.println("Election has been started...");
			for(int i=0; i<num; i++){
				int index = (starter + i) % num;
				if(alive[index]){
					list.add(index);
					System.out.println("Election array: "+list);
				}
			}
			
			int leader = list.get(0);
			for(int i=0; i<list.size(); i++){
				if(list.get(i)>leader){
					leader = list.get(i);
				}
			}
			
			System.out.println("\n - New Coordinator Elected is: "+leader);
			for(int i=0; i<num; i++){
				if(alive[i] && i!=leader){
					System.out.println("Coordinator "+leader+" updates msg to processID: "+i);
				}
			}
			
		}else{
			System.out.println("Coordinator is alive no need for election");
		}
	}
}

/*
vboxuser@Ubuntu:~/Desktop/practice/06 Bully Ring$ javac Ring.java
vboxuser@Ubuntu:~/Desktop/practice/06 Bully Ring$ java Ring
Total number of process are 1-7
Iniial Coordinator: 7
 - Enter crashed processID: 7
 - Enter starter processID: 4
Election has been started...
Election array: [4]
Election array: [4, 5]
Election array: [4, 5, 6]
Election array: [4, 5, 6, 0]
Election array: [4, 5, 6, 0, 1]
Election array: [4, 5, 6, 0, 1, 2]
Election array: [4, 5, 6, 0, 1, 2, 3]

 - New Coordinator Elected is: 6
Coordinator 6 updates msg to processID: 0
Coordinator 6 updates msg to processID: 1
Coordinator 6 updates msg to processID: 2
Coordinator 6 updates msg to processID: 3
Coordinator 6 updates msg to processID: 4
Coordinator 6 updates msg to processID: 5

*/
