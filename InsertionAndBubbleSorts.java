import java.util.concurrent.TimeUnit;

public class MainQuadratic {
    static long last = 42;
    static final long a = 25214903917L; // Multiplier
    static final long c = 11; // Increment
    static final long m = 1L << 48; // Modulus

    static int nextInt(int bound) {
        last = (a * last + c) % m;
        return (int) (last % bound);
    }

    // Insertion Sort
    static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    static void bubbleSort(int arr[]){
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {

                    // Swap arr[j] and arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
    }

    public static void main(String[] args) {
        final int MAX_N = 6000;

        for(int N = 100; N <= MAX_N; N *= 1.5) {
            int[] arr = new int[N];

            final int M = 10;

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
                bubbleSort(arr);

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
