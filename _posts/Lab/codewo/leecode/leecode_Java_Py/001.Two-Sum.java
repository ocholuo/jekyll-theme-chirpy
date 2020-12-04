
// 1. Two Sum Easy
// https://leetcode.com/problems/two-sum/submissions/
// Given an array of integers, return indices of the two numbers such that they add up to a specific target.
// You may assume that each input would have exactly one solution, and you may not use the same element twice.
//
// Example:
// Given nums = [2, 7, 11, 15], target = 9,
// Because nums[0] + nums[1] = 2 + 7 = 9,
// return [0, 1].



// Approach 1: Brute Force
//
// class Solution {
//     public static int[] twoSum(int[] nums, int target) {
//         int[] result = new int[2];
//         if (nums == null || nums.length == 0) {
//             return result;
//         }
//         for (int i = 0; i < nums.length; i++) {
//             for (int j = i + 1; j < nums.length; j++) {
//                 if (nums[i] == target - nums[j]) {
//                     result[0] = i;
//                     result[1] = j;
//                     return result;
//                 }
//             }
//         }
//         return result;
//     }
// }
// Runtime: 89 ms, faster than 10.51% of Java online submissions for Two Sum.
// Memory Usage: 39.5 MB, less than 71.20% of Java online submissions for Two Sum.



// public int[] twoSum(int[] nums, int target) {
//     for (int i = 0; i < nums.length; i++) {
//         for (int j = i + 1; j < nums.length; j++) {
//             if (nums[j] == target - nums[i]) {
//                 return new int[] { i, j };
//             }
//         }
//     }
//     throw new IllegalArgumentException("No two sum solution");
// }
// Runtime: 110 ms, faster than 6.76% of Java online submissions for Two Sum.
// Memory Usage: 40.8 MB, less than 10.59% of Java online submissions for Two Sum.
// Time complexity : O(n^2) For each element, we try to find its complement by looping through the rest of array which takes O(n)O(n) time. Therefore, the time complexity is O(n^2)
//
// Space complexity : O(1)O(1).




// Approach 2: Two-pass Hash Table
//
// A hash table.
// more efficient way to check if the complement exists in the array.
// the best way to maintain a mapping of each element in the array to its index.
//
// reduce the look up time from O(n) to O(1) by trading space for speed. A hash table is built exactly for this purpose, it supports fast look up in near constant time.
//
//
// class Solution {
//     public int[] twoSum(int[] nums, int target) {
//         Map<Integer, Integer> map = new HashMap<>();
//
//         for (int i = 0; i < nums.length; i++) {
//             map.put(nums[i], i);
//             //  {value=index}
//         }
//
//         for (int i = 0; i < nums.length; i++) {
//             int complement = target - nums[i];
//             if (map.containsKey(complement) && map.get(complement) != i) {
//                 return new int[] { i, map.get(complement) };
//             }
//         }
//         throw new IllegalArgumentException("No two sum solution");
//     }
// }
// Runtime: 2 ms, faster than 76.21% of Java online submissions for Two Sum.
// Memory Usage: 39.4 MB, less than 77.83% of Java online submissions for Two Sum.



// Approach 3: One-pass Hash Table
// It turns out we can do it in one-pass. While we iterate and inserting elements into the table, we also look back to check if current elements complement already exists in the table. If it exists, we have found a solution and return immediately.


// public int[] twoSum(int[] nums, int target) {
//     Map<Integer, Integer> map = new HashMap<>();
//     for (int i = 0; i < nums.length; i++) {
//         int complement = target - nums[i];
//         if (map.containsKey(complement)) {
//             return new int[] { map.get(complement), i };
//         }
//         map.put(nums[i], i);
//     }
//     throw new IllegalArgumentException("No two sum solution");
// }
// Runtime: 1 ms, faster than 99.96% of Java online submissions for Two Sum.
// Memory Usage: 41.7 MB, less than 5.73% of Java online submissions for Two Sum.

// Time complexity : O(n). We traverse the list containing nn elements only once. Each look up in the table costs only O(1)O(1) time.
//
// Space complexity : O(n). The extra space required depends on the number of items stored in the hash table, which stores at most nn elements.




// Java 8, only 3ms runtime (99.94% faster than all submissions):
//
// class Solution {
//     public int[] twoSum(int[] nums, int target) {
//       Hashtable<Integer, Integer> hashTable = new Hashtable<Integer, Integer>();
//       int i = 0;
//       while ((i < nums.length) && (hashTable.get(nums[i]) == null)) {
//         hashTable.put(target - nums[i], i);
//         i++;
//       }
//       if (i < nums.length) {
//         return new int[]{hashTable.get(nums[i]),i};
//       }
//       return null;
//     }
// }
















//
