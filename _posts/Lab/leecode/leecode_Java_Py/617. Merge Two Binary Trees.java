// import java.util.Stack;

// 617. Merge Two Binary Trees
// https://leetcode.com/problems/merge-two-binary-trees/
// Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

// You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

// Example 1:
// Input: 
// 	Tree 1                     Tree 2                  
//           1                         2                             
//          / \                       / \                            
//         3   2                     1   3                        
//        /                           \   \                      
//       5                             4   7                  

// Output: 
// Merged tree:
// 	     3
// 	    / \
// 	   4   5
// 	  / \   \ 
// 	 5   4   7

// Note: The merging process must start from the root nodes of both trees.




/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */


// Approach #1 Using Recursion [Accepted]
// class Solution {
//     public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
//         if (t1 == null)
//             return t2;
//         if (t2 == null)
//             return t1;
//         t1.val += t2.val;
//         t1.left = mergeTrees(t1.left, t2.left);
//         t1.right = mergeTrees(t1.right, t2.right);
//         return t1;
//     }
// }
// Complexity Analysis
// Time complexity : O(m). A total of mm nodes need to be traversed. Here, mm represents the minimum number of nodes from the two given trees.
// Space complexity : O(m). The depth of the recursion tree can go upto mm in the case of a skewed tree. In average case, depth will be O(logm)O(logm).
// Runtime: 0 ms, faster than 100.00% of Java online submissions for Merge Two Binary Trees.
// Memory Usage: 39.6 MB, less than 39.84% of Java online submissions for Merge Two Binary Trees.


// Approach #2 Iterative Method [Accepted] ????????
// class Solution {
//     public class Solution {
//         public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
//             if (t1 == null)
//                 return t2;

//             Stack <TreeNode[]> stack = new Stack< >();
//             stack.push(new TreeNode[] {t1, t2});

//             while (!stack.isEmpty()) {
//                 TreeNode[] t = stack.pop();

//                 if (t[0] == null || t[1] == null) {
//                     continue;
//                 }
//                 t[0].val += t[1].val;

//                 if (t[0].left == null) {
//                     t[0].left = t[1].left;
//                 } 
//                 else {
//                     stack.push(new TreeNode[] {t[0].left, t[1].left});
//                 }

//                 if (t[0].right == null) {
//                     t[0].right = t[1].right;
//                 } 
//                 else {
//                     stack.push(new TreeNode[] {t[0].right, t[1].right});
//                 }
//             }
//             return t1;
//         }
//     }    
// }
// Runtime: 2 ms, faster than 9.22% of Java online submissions for Merge Two Binary Trees.
// Memory Usage: 49.2 MB, less than 5.01% of Java online submissions for Merge Two Binary Trees.




// 