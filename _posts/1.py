

import math as mt 

def findTriplets(arr, Sum): 
    n = len(arr) 
    count = 0
    for i in range(n - 1): 
        s = dict() 
        for j in range(i + 1, n): 
            #  the third number x
            x = Sum - (arr[i] + arr[j]) 
            if x in s.keys(): 
                print(arr[i], arr[j], x) 
                count +=1
            else: 
                s[arr[j]] = 1
    print(s)
    return count

# Driver code 
arr = [1,2,3,4,5] 
Sum = 8
n = findTriplets(arr, Sum) 
print(n)


# def triplets(x, a):
#     n = len(a)
#     if n < 3:
#         return 0
#     res = 0
#     a = [i for i in a if i < x]
#     a.sort()
#     for i in range(n - 2):
#         j, k = i + 1, n - 1
#         while j < k:
#             if a[i] + a[j] + a[k] > x:
#                 k -= 1
#             else:
#                 res += k - j
#                 j += 1
#     return res