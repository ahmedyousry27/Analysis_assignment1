import time

from matplotlib import pyplot as plt


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
        iterative_Method_powerNumber(2, n)  # Using base 2, f
        end_time = time.time()
        iterative_times.append(end_time - start_time)

        start_time = time.time()
        divide_and_conquer_powerNumber(2, n)  # Using base 2, for example
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
    plt.title('Power Number')
    plt.legend()
    plt.show()

if __name__ == '__main__':
    print(divide_and_conquer_powerNumber(2,4))
    graph()
