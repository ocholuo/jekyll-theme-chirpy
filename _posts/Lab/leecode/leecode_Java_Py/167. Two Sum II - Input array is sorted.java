// import java.util.HashMap;

// 167. Two Sum II - Input array is sorted
// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
// Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
// The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
//
// Note:
// Your returned answers (both index1 and index2) are not zero-based.
// You may assume that each input would have exactly one solution and you may not use the same element twice.
//
// Example:
// Input: numbers = [1, 2,7,11,15], target = 9
// Output: [1,2]
// Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.


// class Solution {
//     public int[] twoSum(int[] numbers, int target) {
//         for (int i = 0; i < numbers.length; i++) {
//             for (int j = i+1; j < numbers.length; j++) {
//                 int answer = target - numbers[i];
//                 if (numbers[j]== answer) {
//                     return new int[] {i+1, j+1};
//                 }
//             }
//         }
//         throw new IllegalArgumentException("No two sum solution");
//     }
// }
// Runtime: 317 ms, faster than 5.08% of Java online submissions for Two Sum II - Input array is sorted.
// Memory Usage: 42.1 MB, less than 5.01% of Java online submissions for Two Sum II - Input array is sorted.



// hashtable
// class Solution {
//     public int[] twoSum(int[] numbers, int target) {
//         HashMap<Integer,Integer> m = new HashMap<Integer,Integer>();
//         // {number=index}
//         for (int i = 0; i < numbers.length; i++) {
//             int answer = target - numbers[i];
//             if (m.containsKey(answer)) {
//                 return new int[] { m.get(answer)+1, i+1};
//             }
//             m.put(numbers[i], i);
//         }
//         throw new IllegalArgumentException("No two sum solution");
//     }
// }

// Runtime: 2 ms, faster than 26.58% of Java online submissions for Two Sum II - Input array is sorted.
// Memory Usage: 42.1 MB, less than 5.01% of Java online submissions for Two Sum II - Input array is sorted.



// use l and r to denote the first index and second index respectively.
// When the sum is:
// smaller than the target:
// we move l to the right by 1. we can't make r smaller because that's gonna make the sum even smaller.
// bigger than target:
// move r to the left by 1. we can't make l bigger because that's gonna make the sum even bigger.
// equal to the target:
// we found the answer and return.
// assume that each input would have exactly one solution and you may not use the same element twice!!!

// class Solution {
//     public int[] twoSum(final int[] numbers, final int target) {
//         int l = 0, r = numbers.length - 1;
//         while (numbers[l] + numbers[r] != target) {
//             if (numbers[l] + numbers[r] > target) r--;
//             else l++;
//         }
//         return new int[]{l + 1, r + 1};
//     }
//     Runtime: 0 ms, faster than 100.00% of Java online submissions for Two Sum II - Input array is sorted.
//     Memory Usage: 39.3 MB, less than 77.16% of Java online submissions for Two Sum II - Input array is sorted.
// }


// as only i answer, and sort by order

// class Solution {
//     public int[] twoSum(final int[] numbers, final int target) {
//         int i = 0;
//         int j = numbers.length - 1;
//         while (numbers[i] + numbers [j] != target) {
//             if (numbers[i] + numbers [j] > target) {
//                 j--;   
//             }
//             else i++;
//         }
//         return new int[] {i+1, j+1};
//     }
//     Runtime: 1 ms, faster than 64.88% of Java online submissions for Two Sum II - Input array is sorted.
//     Memory Usage: 41.6 MB, less than 7.09% of Java online submissions for Two Sum II - Input array is sorted.
// }


















//
