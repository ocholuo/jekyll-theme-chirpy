
// 169. Majority Element
// Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

// You may assume that the array is non-empty and the majority element always exist in the array.

// Example 1:
// Input: [3,2,3]
// Output: 3

// Example 2:
// Input: [2,2,1,1,1,2,2]
// Output: 2



// Approach 1: Brute Force
// class Solution {
//     public int majorityElement(int[] nums) {
//         int balancenumber =  nums.length/2;
//         for(int num : nums) {
//             int count = 0;
//             for (int counts : nums) {
//                 if(num == counts) {
//                     count +=1;
//                 }
//             }
//             if(count > balancenumber) {
//                 return num;
//             }
//         }
//     return -1;
//     }
// }
// The brute force algorithm iterates over the array, and then iterates again for each number to count its occurrences. As soon as a number is found to have appeared more than any other can possibly have appeared, return it.
// Time complexity : O(n^2)
// The brute force algorithm contains two nested for loops that each run for n iterations, adding up to quadratic time complexity.
// Space complexity : O(1)
// The brute force solution does not allocate additional space proportional to the input size.

// Runtime: 1791 ms, faster than 5.01% of Java online submissions for Majority Element.
// Memory Usage: 50.8 MB, less than 30.91% of Java online submissions for Majority Element.




// Approach 2: HashMap
// We can use a HashMap that maps elements to counts in order to count occurrences in linear time by looping over nums. Then, we simply return the key with maximum value.
// class Solution {

//     // make a hash map(num, frecuency)
//     private Map<Integer, Integer> hmbuilder(int[] nums) {
//         HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
//         for(int num : nums) {
//             if(!hm.containsKey(num)) {
//                 hm.put(num, 1);
//             }
//             else {
//                 hm.put(num, hm.get(num, 1)+1);
//             }
//         }
//         return hm;
//     }

//     public int majorityElement(int[] nums) {
//         Map<Integer, Integer> hm = hmbuilder(nums);
//         Map.Entry<Integer, Integer> majorityEntry = null;
//         for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
//             if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
//                 majorityEntry = entry;
//             }
//         }
//         return majorityEntry.getKey();
//     } 
// }
// Time complexity : O(n)
// We iterate over nums once and make a constant time HashMap insertion on each iteration. Therefore, the algorithm runs in O(n)O(n) time.
// Space complexity : O(n)



// Approach 3: Sorting
// The majority element is the element that appears more than ⌊ n/2 ⌋ times.
// class Solution {

//     public int majorityElement(int[] nums) {
//         Arrays.sort(nums);
//         return nums[nums.length/2];
//     }
// }
// Runtime: 1 ms, faster than 99.93% of Java online submissions for Majority Element.
// Memory Usage: 42.6 MB, less than 92.36% of Java online submissions for Majority Element.



// Approach 4: Randomization
// more than half array indices are occupied by the majority element, a random array index is likely to contain the majority element.
// Algorithm
// Because a given index is likely to have the majority element, we can just select a random index, check whether its value is the majority element, return if it is, and repeat if it is not. The algorithm is verifiably correct because we ensure that the randomly chosen value is the majority element before ever returning.
// class Solution {
//     private int randRange(Random rand, int min, int max) {
//         return rand.nextInt(max - min) + min;
//     }

//     private int countOccurences(int[] nums, int num) {
//         int count = 0;
//         for (int i = 0; i < nums.length; i++) {
//             if (nums[i] == num) {
//                 count++;
//             }
//         }
//         return count;
//     }

//     public int majorityElement(int[] nums) {
//         Random rand = new Random();

//         int majorityCount = nums.length/2;

//         while (true) {
//             int candidate = nums[randRange(rand, 0, nums.length)];
//             if (countOccurences(nums, candidate) > majorityCount) {
//                 return candidate;
//             }
//         }
//     }
// }
// Runtime: 2 ms, faster than 76.54% of Java online submissions for Majority Element.
// Memory Usage: 50.9 MB, less than 27.00% of Java online submissions for Majority Element.