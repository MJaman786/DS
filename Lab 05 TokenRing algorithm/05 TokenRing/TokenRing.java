import java.util.Scanner;

public class TokenRing{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter total number of rings: ");
		int num = sc.nextInt();
		
		System.out.println("Ring Sequence formed: ");
		for(int i=0; i<num; i++){
			System.out.print(" "+i+" ->");
		}
		System.out.println(" 0");
		
		int choice = 0;
		int token = 0;
		do{
			System.out.print("Enter sender node: ");
			int sender = sc.nextInt();
			System.out.print("Enter receiver node: ");
			int receiver = sc.nextInt();
			System.out.print("Enter data to transfer: ");
			String data = sc.next();
			
			System.out.println("Token Passing Sequence: ");
			for(int i=token; i!=sender; i=(i+1)%num){
				System.out.print(" "+i+" ->");
			}
			System.out.println(" "+sender);
			
			for(int i=sender; i!=receiver; i=(i+1)%num){
				System.out.println("Data '"+data+"' is forwarded to node: "+i);
			}
			System.out.println("Data '"+data+"' is received by node: "+receiver);
			
			token = sender;
			System.out.println("Now, token is with node: "+token);
			
			System.out.print("\nIf you want to transfer data again press '1' or '0' to exit: ");
			choice = sc.nextInt();
		}while(choice==1);		
	}
}

/*
vboxuser@Ubuntu:~/Desktop/practice/05 TokenRing$ javac TokenRing.java
vboxuser@Ubuntu:~/Desktop/practice/05 TokenRing$ java TokenRing
Enter total number of rings: 10
Ring Sequence formed: 
 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 0
Enter sender node: 3
Enter receiver node: 6
Enter data to transfer: a
Token Passing Sequence: 
 0 -> 1 -> 2 -> 3
Data 'a' is forwarded to node: 3
Data 'a' is forwarded to node: 4
Data 'a' is forwarded to node: 5
Data 'a' is received by node: 6
Now, token is with node: 3

If you want to transfer data again press '1' or '0' to exit: 1
Enter sender node: 6
Enter receiver node: 9
Enter data to transfer: b
Token Passing Sequence: 
 3 -> 4 -> 5 -> 6
Data 'b' is forwarded to node: 6
Data 'b' is forwarded to node: 7
Data 'b' is forwarded to node: 8
Data 'b' is received by node: 9
Now, token is with node: 6

If you want to transfer data again press '1' or '0' to exit: 1
Enter sender node: 9
Enter receiver node: 3
Enter data to transfer: c
Token Passing Sequence: 
 6 -> 7 -> 8 -> 9
Data 'c' is forwarded to node: 9
Data 'c' is forwarded to node: 0
Data 'c' is forwarded to node: 1
Data 'c' is forwarded to node: 2
Data 'c' is received by node: 3
Now, token is with node: 9

If you want to transfer data again press '1' or '0' to exit: 1
Enter sender node: 2
Enter receiver node: 6
Enter data to transfer: abc
Token Passing Sequence: 
 9 -> 0 -> 1 -> 2
Data 'abc' is forwarded to node: 2
Data 'abc' is forwarded to node: 3
Data 'abc' is forwarded to node: 4
Data 'abc' is forwarded to node: 5
Data 'abc' is received by node: 6
Now, token is with node: 2

If you want to transfer data again press '1' or '0' to exit: 0
vboxuser@Ubuntu:~/Desktop/practice/05 TokenRing$ 

*/
