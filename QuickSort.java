import java.util.concurrent.TimeUnit;

public class Main {
    static long last = 42;
    static final long a = 25214903917L; // Multiplier
    static final long c = 11; // Increment
    static final long m = 1L << 48; // Modulus

    static int nextInt(int bound) {
        last = (a * last + c) % m;
        return (int) (last % bound);
    }

    // Quick Sort
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // pi is partitioning index, arr[pi] is now at right place
            int pi = partition(arr, low, high);

            // Recursively sort elements before partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // A utility function to swap two elements
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /* This function takes last element as pivot, places the pivot element at its correct position in sorted array,
    and places all smaller (smaller than pivot) to left of pivot and all greater elements to right of pivot */
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            if (arr[j] < pivot) {
                i++;
                // swap arr[i] and arr[j]
                swap(arr, i, j);
            }
        }
        // swap arr[i+1] and arr[high] (or pivot)
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void main(String[] args) {
        final int MAX_N = 1000_000;

        for(int N = 1000; N <= MAX_N; N *= 2) {

            int[] arr = new int[N];

            final int M = 50;

            long[] sortingTimes = new long[M];

            // Save initial value of last
            long initialLast = last;

            for (int i = 1; i <= M; i++) {
                // Restore initial value of last
                last = i;

                for (int j = 0; j < N; j++) {
                    arr[j] = nextInt(1007);
                }

                long sortStart = System.nanoTime();

                // Sorting algorithm
                quickSort(arr, 0, arr.length - 1);

                long sortStop = System.nanoTime();
                long sortDuration = sortStop - sortStart;

                // Add value of time in array in nanoseconds
                sortingTimes[i - 1] = sortDuration;
            }

            // Average time in nanoseconds
            long averageTime = 0;
            for (int i = 0; i < M; i++) {
                averageTime += sortingTimes[i];
            }
            averageTime /= M;
            System.out.println(averageTime / 1000);
        }
    }
}
