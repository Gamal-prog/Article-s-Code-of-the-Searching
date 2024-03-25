import time

last = 42
a = 25214903917
c = 11
m = 1 << 48

def next_int(bound):
    global last
    last = (a * last + c) % m
    return last % bound

def partition(arr, low, high):
    pivot = arr[high]
    i = low - 1
    for j in range(low, high):
        if arr[j] < pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]
    arr[i + 1], arr[high] = arr[high], arr[i + 1]
    return i + 1

def quick_sort(arr, low, high):
    if low < high:
        pi = partition(arr, low, high)
        quick_sort(arr, low, pi - 1)
        quick_sort(arr, pi + 1, high)

if __name__ == "__main__":
    MAX_N = 1000000
    N = 1000

    M = 50
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
            quick_sort(arr, 0, N - 1)
            sort_stop = time.time()

            sort_duration = (sort_stop - sort_start) * 1e9

            sorting_times[i - 1] = sort_duration

        average_time = sum(sorting_times) / M
        average_time //= 1000
        print(average_time)
        N *= 2
