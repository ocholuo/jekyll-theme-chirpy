

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 448. Find All Numbers Disappeared in an Array
// Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

// Find all the elements of [1, n] inclusive that do not appear in this array.

// Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

// Example:
// Input:
// [4,3,2,7,8,2,3,1]

// Output:
// [5,6]


class Solution {


// 1. all the numbers that we have seen will be marked as negative. In the second iteration, if a value is not marked as negative, it implies we have never seen that index before, so just add it to the return list.
public List<Integer> findDisappearedNumbers(int[] nums) {
    List<Integer> result = new ArrayList<Integer>();
    for(int i = 0; i < nums.length; i++){
        int data = Math.abs(nums[i]) - 1;
        if(nums[data] > 0){
            nums[data] = -nums[data];
        }
    }
    for(int i = 0; i < nums.length; i++){
        if(nums[i] > 0){
            result.add(i+1);
        }
    }
    return result;
}
// Runtime: 4 ms, faster than 93.19% of Java online submissions for Find All Numbers Disappeared in an Array.
// Memory Usage: 48.8 MB, less than 48.53% of Java online submissions for Find All Numbers Disappeared in an Array.


// public List<Integer> findDisappearedNumbers(int[] nums) {
//     List<Integer> ans = new ArrayList<>();
    
//     for(int n : nums){
//         int i = (n < 0 ? -n : n) - 1;       // n < 0 , i = -n;  n > 0 , i = -n
//         nums[i] = nums[i] > 0 ? -nums[i] : nums[i];
//     }
    
//     for(int i = 0; i < nums.length; i++)
//         if(nums[i] > 0)
//             ans.add(i+1);
//     return ans;
// }


// 2
// 算出range 1 to n，hashset去重复，然后看是否有
// 时间复杂度 O(n).
// 空间复杂度 O(n).
    // public List<Integer> findDisappearedNumbers(int[] nums) {
    //     List<Integer> ans = new ArrayList<>();
    //     HashSet<Integer> rangeSet = new HashSet<>();  
    //     HashSet<Integer> numSet = new HashSet<>();      // no duplicate
        
    //     for(int i = 1; i <= nums.length; i++){
    //         rangeSet.add(i);
    //     }
    //     for(int num : nums){
    //         numSet.add(num);
    //     }

    //     for(int i : rangeSet){
    //         if(!numSet.contains(i)){       // form range 1 to n, check
    //             ans.add(i);
    //         }
    //     }
    //     return ans;
    // }

}