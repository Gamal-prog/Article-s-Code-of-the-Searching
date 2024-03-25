import time

last = 42
a = 25214903917
c = 11
m = 1 << 48


def next_int(bound):
    global last
    last = (a * last + c) % m
    return last % bound


def bubble_sort(arr):
    n = len(arr)
    for i in range(n - 1):
        for j in range(0, n - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]


# Function to do insertion sort
def insertionSort(arr):
    # Traverse through 1 to len(arr)
    for i in range(1, len(arr)):

        key = arr[i]

        # Move elements of arr[0..i-1], that are
        # greater than key, to one position ahead
        # of their current position
        j = i - 1
        while j >= 0 and key < arr[j]:
            arr[j + 1] = arr[j]
            j -= 1
        arr[j + 1] = key


if __name__ == "__main__":
    MAX_N = 6000
    N = 100

    M = 10
    sorting_times = [0] * M

    start = time.time()

    initial_last = last

    while N < MAX_N:
        arr = [0] * N
        for i in range(1, M + 1):
            last = i

            for j in range(N):
                arr[j] = next_int(1007)

            sort_start = time.time()
            bubble_sort(arr)
            sort_stop = time.time()

            sort_duration = (sort_stop - sort_start) * 1e9

            sorting_times[i - 1] = sort_duration

        average_time = sum(sorting_times) / M
        average_time //= 1000
        print(average_time)
        N *= 1.5
        N = int(N)
