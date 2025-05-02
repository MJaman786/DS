import java.util.Scanner;

public class TokenRing{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of nodes in ring: ");
		int num = sc.nextInt();
		System.out.println("Sequence of nodes in ring: ");
		for(int i=0; i<num; i++){
			System.out.print(" "+i+" -> ");
		}
		System.out.println("0");
		int choice = 0;
		int token = 0;
		do{
			System.out.println("----");
			System.out.print("Enter sender: ");
			int sender = sc.nextInt();
			
			System.out.print("Enter receiver: ");
			int receiver = sc.nextInt();
			
			System.out.print("Enter data: ");
			String data = sc.next();
			
			System.out.println("Token Passing Sequence: ");
			for(int i=token; i<sender; i++){
				System.out.print(i+" -> ");
			}
			System.out.println(sender);
			
			for(int i=sender; i!=receiver; i=(i+1)%num){
				System.out.println("Data '"+data+"' is forwarded to -> "+i);
			}
			System.out.println("Data '"+data+"' received by receiver: "+receiver);
			
			token = sender;
			System.out.println("Now the Token is with: "+sender);
			System.out.print("If you to enter data again the press '1' or exit press '0': ");
			choice = sc.nextInt();
		}while(choice==1);
	}
}

/*
vboxuser@Ubuntu:~/Desktop/pract$ java Token
Enter number of nodes in ring: 10
Sequence of nodes in ring: 
 0 ->  1 ->  2 ->  3 ->  4 ->  5 ->  6 ->  7 ->  8 ->  9 -> 0
----
Enter sender: 3
Enter receiver: 9
Enter data: a
Token Passing Sequence: 
0 -> 1 -> 2 -> 3
Data 'a' is forwarded to -> 3
Data 'a' is forwarded to -> 4
Data 'a' is forwarded to -> 5
Data 'a' is forwarded to -> 6
Data 'a' is forwarded to -> 7
Data 'a' is forwarded to -> 8
Data 'a' received by receiver: 9
Now the Token is with: 3
If you to enter data again the press '1' or exit press '0': 1
----
Enter sender: 6
Enter receiver: 9
Enter data: b
Token Passing Sequence: 
3 -> 4 -> 5 -> 6
Data 'b' is forwarded to -> 6
Data 'b' is forwarded to -> 7
Data 'b' is forwarded to -> 8
Data 'b' received by receiver: 9
Now the Token is with: 6
If you to enter data again the press '1' or exit press '0': 1
----
Enter sender: 9
Enter receiver: 3
Enter data: c
Token Passing Sequence: 
6 -> 7 -> 8 -> 9
Data 'c' is forwarded to -> 9
Data 'c' is forwarded to -> 0
Data 'c' is forwarded to -> 1
Data 'c' is forwarded to -> 2
Data 'c' received by receiver: 3
Now the Token is with: 9
If you to enter data again the press '1' or exit press '0': 0
vboxuser@Ubuntu:~/Desktop/pract$ 

*/
