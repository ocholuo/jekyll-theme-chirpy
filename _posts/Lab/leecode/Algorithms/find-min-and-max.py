





# K’th Smallest/Largest Element in Unsorted Array | Set 1
# Input: arr[] = {7, 10, 4, 3, 20, 15}
# k = 3
# Output: 7

# Input: arr[] = {7, 10, 4, 3, 20, 15}
# k = 4
# Output: 10


# Method 1 (Simple Solution)
# A simple solution is to sort the given array using a O(N log N) sorting algorithm 
# like Merge Sort, Heap Sort, etc and return the element at index k-1 in the sorted array.
# Time Complexity of this solution is O(N Log N)
# py:
# def kthSmallest(arr, n, k): 
#     arr.sort() 
#     return arr[k-1] 
# java:
# public static int kthSmallest(Integer[] arr, int k) { 
#         Arrays.sort(arr); 
#         return arr[k - 1]; 
#     } 
# public static void main(String[] args) { 
#     Integer arr[] = new Integer[] { 12, 3, 5, 7, 19 }; 
#     int k = 2; 
#     System.out.print("K'th smallest element is " + kthSmallest(arr, k)); 
# } 


# Method 2 (Using Min Heap – HeapSelect)
# https://www.geeksforgeeks.org/binary-heap/

class pair: 
    def __init__(self): 
        self.min = 0
        self.max = 0
  

def getMinMax(arr: list, n: int) -> pair: 
    minmax = pair() 

    # only one element, return it as min and max both 
    if n == 1: 
        minmax.max = arr[0] 
        minmax.min = arr[0] 
        return minmax 
  
    # If there are more than one elements
    if arr[0] > arr[1]: 
        minmax.max = arr[0] 
        minmax.min = arr[1] 
    else: 
        minmax.max = arr[1] 
        minmax.min = arr[0] 
  
    # start
    for i in range(2, n): 
        if arr[i] > minmax.max: 
            minmax.max = arr[i] 
        elif arr[i] < minmax.min: 
            minmax.min = arr[i] 
    return minmax 


# Driver Code 
if __name__ == "__main__": 
    arr = [1000, 11, 445, 1, 330, 3000] 
    arr_size = 6
    minmax = getMinMax(arr, arr_size) 
    print("Minimum element is", minmax.min) 
    print("Maximum element is", minmax.max) 