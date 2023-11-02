
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
if __name__ == '__main__':
    print(divide_and_conquer_powerNumber(2,4))

