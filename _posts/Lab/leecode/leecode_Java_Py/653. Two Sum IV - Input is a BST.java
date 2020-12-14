


// 653. Two Sum IV - Input is a BST
// https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
// Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

// Example 1:
// Input: 
//     5
//    / \
//   3   6
//  / \   \
// 2   4   7
// Target = 9
// Output: True

// Example 2:
// Input: 
//     5
//    / \
//   3   6
//  / \   \
// 2   4   7
// Target = 28
// Output: False


// Definition for a binary tree node.
// public class TreeNode {

//     int val;
//     TreeNode left;
//     TreeNode right;
//     TreeNode() {}

//     TreeNode(int val) { 
//         this.val = val; 
//     }

//     TreeNode(int val, TreeNode left, TreeNode right) {
//         this.val = val;
//         this.left = left;
//         this.right = right;
//     }
// }




// Approach #1 Using HashSet[Accepted]
// The simplest solution will be to traverse over the whole tree and consider every possible pair of nodes to determine if they can form the required sum kk. But, we can improve the process if we look at a little catch here.

// If the sum of two elements x + y equals k, already know that x exists in the given tree, only need to check if an element y exists in the given tree
// Based on this simple catch, can traverse the tree in both the directions(left child and right child) at every step. We keep a track of the elements which have been found so far during the tree traversal, by putting them into a setset.

// For every current node with a value of pp, we check if k-pk−p already exists in the array. If so, we can conclude that the sum kk can be formed by using the two elements from the given tree. Otherwise, we put this value pp into the setset.

// If even after the whole tree's traversal, no such element pp can be found, the sum kk can't be formed by using any two elements.




















// 