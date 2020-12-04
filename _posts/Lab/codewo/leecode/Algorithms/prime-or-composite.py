


# list, find a number is prime or composite
inputList = ['10','1','2','3','4','5','6','7','8','9','10','11']


import sys

def isPrime(num):
    if num == 1: return True
    if num > 1:
        for i in range(2,num):
            if (num % i) == 0:
                return False
        else:
            return True
            
def prmiOrComp(inputList):
    outputstr = ''
    for num in inputList[1:]:
        if isPrime(int(num)):
            outputstr += 'Prime '
        else: 
            outputstr += 'Composite '
    print(outputstr)

prmiOrComp(inputList)

print(isPrime(4))
print(4%2)