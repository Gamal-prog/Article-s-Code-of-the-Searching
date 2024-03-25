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

void quickSort(std::vector<int>& arr, int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

int main() {
    freopen("result_cpp.txt", "w", stdout);
    const int MAX_N = 1000000;

    long long initialLast = last;

    for(int N = 1000; N <= MAX_N; N *= 2) {

        std::vector<int> arr(N);

        const int M = 50;
        std::vector<long long> sortingTimes(M);

        for (int i = 1; i <= M; ++i) {
            last = i;

            for (int j = 0; j < N; ++j) {
                arr[j] = nextInt(1007);
            }

            auto sortStart = std::chrono::high_resolution_clock::now();

            quickSort(arr, 0, N - 1);

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

