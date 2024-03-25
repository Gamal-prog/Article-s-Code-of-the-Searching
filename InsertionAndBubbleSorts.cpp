#include <iostream>
#include <vector>
#include <chrono>

long long last = 42;
const long long a = 25214903917LL;
const long long c = 11;
const long long m = 1LL << 48;

int nextInt(int bound) {
    last = (a * last + c) % m;
    return last % bound;
}

int partition(std::vector<int>& arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;
    for (int j = low; j < high; ++j) {
        if (arr[j] < pivot) {
            ++i;
            std::swap(arr[i], arr[j]);
        }
    }
    std::swap(arr[i + 1], arr[high]);
    return i + 1;
}

void bubbleSort(std::vector<int>& arr, int n)
{
    int i, j;
    bool swapped;
    for (i = 0; i < n - 1; i++) {
        swapped = false;
        for (j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                std::swap(arr[j], arr[j + 1]);
                swapped = true;
            }
        }

        // If no two elements were swapped
        // by inner loop, then break
        if (swapped == false)
            break;
    }
}

void insertionSort(std::vector<int>& arr, int n)
{
    int i, key, j;
    for (i = 1; i < n; i++) {
        key = arr[i];
        j = i - 1;

        // Move elements of arr[0..i-1],
        // that are greater than key,
        // to one position ahead of their
        // current position
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}

int main() {
    const int MAX_N = 6000;

    long long initialLast = last;

    for(int N = 100; N <= MAX_N; N *= 1.5) {

        std::vector<int> arr(N);

        const int M = 10;
        std::vector<long long> sortingTimes(M);

        for (int i = 1; i <= M; ++i) {
            last = i;

            for (int j = 0; j < N; ++j) {
                arr[j] = nextInt(1007);
            }

            auto sortStart = std::chrono::high_resolution_clock::now();

            bubbleSort(arr, N);

            auto sortStop = std::chrono::high_resolution_clock::now();
            long long sortDuration = std::chrono::duration_cast<std::chrono::nanoseconds>(sortStop - sortStart).count();

            sortingTimes[i - 1] = sortDuration;
        }

        long long averageTime = 0;

        for (int i = 0; i < M; ++i) {
            averageTime += sortingTimes[i];
        }
        long averageTimeInNanoseconds = averageTime / M;
        // std::cout << "Average Time for array << averageTimeInNanoseconds << " nanoseconds" << std::endl;
        std::cout << averageTimeInNanoseconds / 1000 << std::endl;
    }
    return 0;
}
