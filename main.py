import random
import time

import numpy as np
from matplotlib import pyplot as plt
from numpy.random import randint


def iterative_Method_powerNumber(a,n):
    result=1
    for i in range(n):
        result *= a
    return result

def divide_and_conquer_powerNumber(a,n) :
    if n== 1:
        return a
    elif n %2 ==0 :
        return divide_and_conquer_powerNumber(a, n//2) * divide_and_conquer_powerNumber(a, n//2)
    else :
        return a * divide_and_conquer_powerNumber(a, (n-1)//2) * divide_and_conquer_powerNumber(a, (n-1)//2)

# Press the green button in the gutter to run the script.
def graph():

    common_difference = (10 ** 6 - 1) // 7
    n_array = [1 + i * common_difference for i in range(8)]
    iterative_times = []
    divide_and_conquer_times = []

    for n in n_array:
        start_time = time.time()
        iterative_Method_powerNumber(2, n)
        end_time = time.time()
        iterative_times.append(end_time - start_time)

        start_time = time.time()
        divide_and_conquer_powerNumber(2, n)
        end_time = time.time()
        divide_and_conquer_times.append(end_time - start_time)

    # Plotting the results
    plt.figure(figsize=(10, 6))
    plt.plot(n_array, iterative_times, label='Iterative Method')
    plt.plot(n_array, divide_and_conquer_times, label='divide_and_conquer')
    plt.yscale('log')
    plt.xscale('log')
    plt.xlabel('n')
    plt.ylabel('Time (seconds)')
    plt.title('Powering a Number')
    plt.legend()
    plt.show()




def merge(arr, l, m, r):
    n1 = m - l + 1
    n2 = r - m

    L = [0] * (n1)
    R = [0] * (n2)

    for i in range(0, n1):
        L[i] = arr[l + i]

    for j in range(0, n2):
        R[j] = arr[m + 1 + j]

    i = 0
    j = 0
    k = l

    while i < n1 and j < n2:
        if L[i] <= R[j]:
            arr[k] = L[i]
            i += 1
        else:
            arr[k] = R[j]
            j += 1
        k += 1


    while i < n1:
        arr[k] = L[i]
        i += 1
        k += 1


    while j < n2:
        arr[k] = R[j]
        j += 1
        k += 1




def mergeSort(arr, l, r):
    if l < r:
        m = l + (r - l) // 2
        mergeSort(arr, l, m)
        mergeSort(arr, m + 1, r)
        merge(arr, l, m, r)



def binary_search(arr, low, high, target):
    while low <= high:
        mid = (low + high) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            low = mid + 1
        else:
            high = mid - 1
    return -1
def find_pairs_with_sum(arr, target):
    mergeSort(arr,0,len(arr)-1)
    pairs = []
    for i in range(len(arr)):
        complement = target - arr[i]
        """ it handles not appearing same number twice if their add equal target but it will only  appear if there input has a duplicate """
        if binary_search(arr, 0, len(arr) - 1, complement) !=-1 and arr[i] <= complement and binary_search(arr, 0, len(arr) - 1, complement) !=i:
            pairs.append((arr[i], complement))
    return pairs

def measure_running_time():
    array_length=[10,100,500,1000,5000,10000,50000,100000,500000,1000000]
    execution_times = []

    for n in array_length:
        arr = randint(1,30,n)
        start_time = time.time()
        find_pairs_with_sum(arr, target=15)
        end_time = time.time()
        execution_times.append(end_time - start_time)


    plt.plot(array_length, execution_times, marker='o')
    plt.xscale('log')
    plt.yscale('log')
    plt.xlabel('Input Size (n)')
    plt.ylabel('Execution Time (seconds)')
    plt.title('find_pairs_with_sum')
    plt.grid(True)
    plt.show()




if __name__ == '__main__':
    S = [8, 5, 10, 15, 9, 1, 2, 9]
    target = 10
    print(find_pairs_with_sum(S, target))








