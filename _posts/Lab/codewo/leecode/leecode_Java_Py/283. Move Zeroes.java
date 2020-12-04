



// 283. Move Zeroes
// Given an array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

// Example:
// Input: [0,1,0,3,12]
// Output: [1,3,12,0,0]

// Input :  arr[] = {1, 2, 0, 4, 3, 0, 5, 0};
// Output : arr[] = {1, 2, 4, 3, 5, 0, 0};

// Input : arr[]  = {1, 2, 0, 0, 0, 3, 6};
// Output : arr[] = {1, 2, 3, 6, 0, 0, 0};

// Note:
// You must do this in-place without making a copy of the array.
// Minimize the total number of operations.


// public class MoveZero {

//     // Function which pushes all zeros to end of an array. 
//     static void pushZerosToEnd(int nums[]) { 
//         int count = 0;  // Count of non-zero elements 
//         int n = nums.length; 

//         for (int i = 0; i < n; i++) {
//             if (nums[i] != 0) {
//                 nums[count++] = nums[i]; 
//             }
//         }
//         while (count < n) {
//             nums[count++] = 0; 
//         }
//     }

    

//     public static void main (String[] args) { 
//         int arr[] = {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9}; 
//         pushZerosToEnd(arr); 
//         System.out.println("Array after pushing zeros to the back: "); 
//         for (int i=0; i<n; i++) {
//             System.out.print(arr[i]+" "); 
//         }
//     }
// }
// Runtime: 0 ms, faster than 100.00% of Java online submissions for Move Zeroes.
// Memory Usage: 39.8 MB, less than 71.68% of Java online submissions for Move Zeroes.