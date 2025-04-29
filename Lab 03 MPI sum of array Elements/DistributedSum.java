// Thread class to calculate partial sum of a portion of the array
class SumThread extends Thread {
    int[] arr;
    int start, end;
    int partialSum = 0;

    // Constructor to assign array part
    SumThread(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    // This method runs when thread is started
    public void run() {
        for (int i = start; i < end; i++) {
            partialSum += arr[i]; // Calculate sum of assigned portion
        }
        System.out.println("Thread for index " + start + " to " + (end - 1) + " calculated sum: " + partialSum);
    }

    // Getter method to retrieve partial sum after thread is done
    public int getPartialSum() {
        return partialSum;
    }
}

// Main class to run the distributed sum example
public class DistributedSum {
    public static void main(String[] args) throws InterruptedException {
        // Array whose sum needs to be calculated
        int[] array = { 2, 4, 6, 8, 10, 12, 14, 16 }; // N = 8 elements

        int n = 4; // Number of threads (processors)
        int length = array.length;
        int chunkSize = length / n; // Divide array into equal parts

        SumThread[] threads = new SumThread[n]; // Array to hold thread objects

        int start = 0; // Start index of array for each thread

        // Create and start each thread
        for (int i = 0; i < n; i++) {
            int end = start + chunkSize;

            // If it's the last thread, include any remaining elements
            if (i == n - 1) {
                end = length;
            }

            // Create and start thread
            threads[i] = new SumThread(array, start, end);
            threads[i].start();

            start = end; // Update start for next chunk
        }

        int totalSum = 0;

        // Wait for all threads to finish and collect their partial sums
        for (int i = 0; i < n; i++) {
            threads[i].join(); // Wait for thread i to finish
            totalSum += threads[i].getPartialSum(); // Add its result to total
        }

        // Print final result
        System.out.println("Total sum = " + totalSum);
    }
}
/*
 * Thread for index 0 to 1 calculated sum: 6
 * Thread for index 4 to 5 calculated sum: 22
 * Thread for index 2 to 3 calculated sum: 14
 * Thread for index 6 to 7 calculated sum: 30
 * Total sum = 72
 */