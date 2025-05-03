import java.util.Scanner;

class Mythread extends Thread {
    int[] arr;
    int start, end;
    int partialSum = 0;

    Mythread(int arr[], int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i < end; i++) {
            partialSum += arr[i];
        }
        System.out.println("Thread for index " + start + "-" + end + " calculated sum: " + partialSum);
    }

    public int returnPartialSum() {
        return partialSum;
    }

}

public class DistributedSumMain {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of Elements: ");
        int num = sc.nextInt();
        int[] arr = new int[num];
        System.out.println(" - Enter Elements of array:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("  - Element at index '" + i + "': ");
            arr[i] = sc.nextInt();
        }
        int length = arr.length;
        System.out.print("Enter number of threads: ");
        int numOfTh = sc.nextInt();
        int chunckSize = length / numOfTh;
        System.out.println("Chuncksize: " + chunckSize);
        Mythread[] thread = new Mythread[numOfTh];

        int start = 0;
        for (int i = 0; i < numOfTh; i++) {
            int end = start + chunckSize;
            if (i == numOfTh - 1) {
                end = length;
            }
            thread[i] = new Mythread(arr, start, end);
            thread[i].start();
            start = end;
        }

        int total = 0;
        for (int i = 0; i < numOfTh; i++) {
            thread[i].join();
            total += thread[i].returnPartialSum();
        }

        System.out.println("Total sum of Elements: " + total);
    }
}
/*
 * vboxuser@Ubuntu:~/Desktop/pract$ java DistributedSum
 * Enter number of Elements: 10
 * - Enter Elements of array:
 * - Element at index '0': 1
 * - Element at index '1': 2
 * - Element at index '2': 3
 * - Element at index '3': 4
 * - Element at index '4': 5
 * - Element at index '5': 6
 * - Element at index '6': 7
 * - Element at index '7': 8
 * - Element at index '8': 9
 * - Element at index '9': 1
 * Enter number of threads: 5
 * Chuncksize: 2
 * Thread for index 6-8 calculated sum: 15
 * Thread for index 4-6 calculated sum: 11
 * Thread for index 0-2 calculated sum: 3
 * Thread for index 2-4 calculated sum: 7
 * Thread for index 8-10 calculated sum: 10
 * Total sum of Elements: 46
 * 
 */
