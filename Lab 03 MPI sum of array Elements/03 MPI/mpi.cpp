#include<iostream>
#include<mpi.h>
using namespace std;

int main(int argc, char* argv[]) {
    int rank, size, num = 8;
    int data[num] = {1, 2, 3, 4, 5, 6, 7, 8};

    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    int chunkSize = num / size;
    int subArray[chunkSize]; // Each process gets chunkSize elements

    // Scatter only the first chunkSize * size elements
    MPI_Scatter(
    	data, 
    	chunkSize, 
    	MPI_INT,
    	subArray, 
    	chunkSize, 
    	MPI_INT,
    	0, 
    	MPI_COMM_WORLD
   	);

    // Each process computes its local sum
    int partialSum = 0;
    for (int i = 0; i < chunkSize; i++) {
        partialSum += subArray[i];
    }

    // Root process adds leftover elements
    if (rank == 0) {
        for (int i = chunkSize * size; i < num; i++) {
            partialSum += data[i];
        }
    }

    // Show partial sum
    cout << "Process " << rank << " computed partialsum: " << partialSum << endl;

    // Reduce to get total sum at root
    int totalSum = 0;
    MPI_Reduce(
    	&partialSum, 
    	&totalSum, 
    	1, 
    	MPI_INT, 
    	MPI_SUM, 
    	0, 
    	MPI_COMM_WORLD	
    );

    if (rank == 0) {
        cout << "Total summation of array: " << totalSum << endl;
    }

    MPI_Finalize();
    return 0;
}

/*
vboxuser@Ubuntu:~/Desktop/practice/03 MPI$ sudo apt install openmpi-bin openmpi-common libopenmpi-dev
vboxuser@Ubuntu:~/Desktop/practice/03 MPI$ mpic++ -o mpi mpi.cpp
vboxuser@Ubuntu:~/Desktop/practice/03 MPI$ mpirun -oversubscribe -np 4 ./mpi
Process 0 computed partialsum: 3
Process 2 computed partialsum: 11
Process 1 computed partialsum: 7
Process 3 computed partialsum: 15
Total summation of subarray's: 36

vboxuser@Ubuntu:~/Desktop/practice/03 MPI$ mpirun -oversubscribe -np 3 ./mpi
Process 0 computed partialsum: 18
Process 1 computed partialsum: 7
Process 2 computed partialsum: 11
Total summation of array: 36

*/
