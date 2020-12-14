



// 136. Single Number 
// https://leetcode.com/problems/single-number/
// Given a non-empty array of integers, every element appears twice except for one. Find that single one.

// Note:
// Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

// Example 1:
// Input: [2,2,1]
// Output: 1

// Example 2:
// Input: [4,1,2,1,2]
// Output: 4



// Approach 1: List operation
// Iterate over all the elements in  nums 
// If some number in nums is new to array, append it
// If some number is already in the array, remove it
class Solution {

    public int singleNumber(int[] nums) {

        List<Integer> newlist = new ArrayList<>();
    
        for (int i : nums) {
            if (!newlist.contains(i)) {
                newlist.add(i);
            } else {
                newlist.remove(new Integer(i));
            }
        }
        return newlist.get(0);
    }
}
// Complexity Analysis
// Time complexity : O(n^2)
// iterate through \text{nums}nums, taking O(n)O(n) time. We search the whole list to find whether there is duplicate number, taking O(n)O(n) time. Because search is in the for loop, so we have to multiply both time complexities which is O(n^2)
// Space complexity : O(n)
// need a list of size nn to contain elements in \text{nums}nums.
// Runtime: 918 ms, faster than 5.01% of Java online submissions for Single Number.
// Memory Usage: 45.9 MB, less than 11.09% of Java online submissions for Single Number.




// Approach 2: Hash Table
// We use hash table to avoid the O(n) time required for searching the elements.
// Iterate through all elements in nums and set up key/value pair.
// Return the element which appeared only once
class Solution {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> hash_table = new HashMap<>();
    
        for (int i : nums) {
            hash_table.put(i, hash_table.getOrDefault(i, 0) + 1);
        }
        for (int i : nums) {
            if (hash_table.get(i) == 1) {
            return i;
            }
        }
        return 0;
    }
}
// Complexity Analysis
// Time complexity : O(n*1)
// Time complexity of for loop is O(n)O(n). Time complexity of hash table(dictionary in python) operation pop is O(1)O(1).
// Space complexity : O(n)
// The space required by hash\_tablehash_table is equal to the number of elements in \text{nums}nums.
// Runtime: 7 ms, faster than 34.55% of Java online submissions for Single Number.
// Memory Usage: 40.4 MB, less than 69.80% of Java online submissions for Single Number.



// Approach 3: Math
// 2 * (a + b + c) - (a + a + b + b + c) = c
class Solution {
    public int singleNumber(int[] nums) {
        int sumOfSet = 0, sumOfNums = 0;
        Set<Integer> set = new HashSet();
    
        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
                sumOfSet += num;
            }
            sumOfNums += num;
        }
        return 2 * sumOfSet - sumOfNums;
    }
}
// Time complexity : O(n + n) = O(n)
// sum will call next to iterate through \text{nums}nums. We can see it as sum(list(i, for i in nums)) which means the time complexity is O(n)O(n) because of the number of elements(nn) in \text{nums}nums.
// Space complexity : O(n + n) = O(n)
// set needs space for the elements in nums
// Runtime: 12 ms, faster than 19.09% of Java online submissions for Single Number.
// Memory Usage: 46.2 MB, less than 8.80% of Java online submissions for Single Number.


