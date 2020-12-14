

// 543. Diameter of Binary Tree
// https://leetcode.com/problems/diameter-of-binary-tree/
// Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

// Example:
// Given a binary tree
//           1
//          / \
//         2   3
//        / \     
//       4   5    
// Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
// Note: The length of path between two nodes is represented by the number of edges between them.


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


// Approach #1: Depth-First Search [Accepted]
// class Solution {

//     public int ans=0;

//     public int diameterOfBinaryTree(TreeNode root) {
//         depth(root);
//         return ans;
//     }
    
//     public int depth(TreeNode root) {
//         if (root == null) {
//             return 0;
//         }
//         int L = depth(root.left);
//         int R = depth(root.right);
//         ans = Math.max(ans, L+R);
//         return Math.max(L, R) + 1;
//     }
// }
// Runtime: 0 ms, faster than 100.00% of Java online submissions for Diameter of Binary Tree.
// Memory Usage: 39.4 MB, less than 51.85% of Java online submissions for Diameter of Binary Tree.