---
title: geeksforgeeks - Numbers whose factorials end with n zeros
date: 2020-09-04 11:11:11 -0400
categories: [Code]
tags: [OnePage, Code, geeksforgeeks]
math: true
image: 
---


# Numbers whose factorials end with n zeros

Given an integer n, we need to find the number of positive integers whose factorial ends with n zeros.

```
Examples:
Input : n = 1
Output : 5 6 7 8 9
Explanation: Here, 5! = 120, 6! = 720,
7! = 5040, 8! = 40320 and 9! = 362880.

Input : n = 2
Output : 10 11 12 13 14 
```

Naive approach:
- iterate through the range of integers
- find the number of trailing zeros of all the numbers
- print the numbers with n trailing zeros.


Efficient Approach:
- use binary search for all the numbers in the range
- get the first number with n trailing zeros. 
- Find all the numbers with m trailing zeros after that number.


```java

public class Solution {

    // find Trailing-zeros-in-factorial n
    public int trailingzeros(int n){
        int count = 0;
        while(n/5 > 0){
            count += n/5;
            n = n/5;
        }
        return count;
    }


    public void binarySearchforZeros(int x){

        // since low start x zeros
        int low = 0;
        int hight = 1000;
        while(low < hight){
            int mid = (low + hight) /2;
            int count = trailingzeros(mid);
            if(count < x){
                low = mid + 1;
            }
            else{
                hight = mid;
            }
        }

        // caculate from low
        int result[] = new int[1000];
        int k = 0;
        while(trailingzeros(low) == x){
            result[k] = low;
            k++;
            low++;
        }

        // print it
        for (int i = 0; i < k; i++) {
            System.out.print(result[i] + " "); 
        }

    }


    public static void main(String[] args) {
        Solution pr = new Solution();
        int n = 3; 
        pr.binarySearchforZeros(n); 
    } 
}

```

.