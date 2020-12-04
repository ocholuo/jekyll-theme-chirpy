

// 938. Range Sum of BST
// https://leetcode.com/problems/range-sum-of-bst/
// Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).
// The binary search tree is guaranteed to have unique values.

// Example 1:
// Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
// Output: 32
//      10
//   5     15
// 3   7     18


// Example 2:
// Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
// Output: 23
//       10
//    5     15
//  3   7     18
// 1 


// Note:
// The number of nodes in the tree is at most 10000.
// The final answerSumwer is guaranteed to be less than 2^31.


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


// 1. 深度优先遍历
// 求出所有 X >= L 且 X <= R 的值的和
// 递归终止条件：
// 当前节点为null时返回0
// 当前节点 X < L 时则返回右子树之和
// 当前节点 X > R 时则返回左子树之和
// 当前节点 L <= X 且 X <= R 时则返回：当前节点值 + 左子树之和 + 右子树之和
// class Solution {
//     public int rangeSumBST(TreeNode root, int L, int R) {
//         if (root == null) { return 0; }
//         if (root.val < L) { return rangeSumBST(root.right, L, R);}
//         if (root.val > R) { return rangeSumBST(root.left, L, R);}
//         return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
//     }
// }
// Runtime: 1 ms, faster than 54.38% of Java online submissions for Range Sum of BST.
// Memory Usage: 62 MB, less than 5.08% of Java online submissions for Range Sum of BST.


// 2. Intuition and Algorithm
// We traverse the tree using a depth first search. If node.val falls outside the range [L, R], (like node.val < L), then only the right branch could have nodes with value inside [L, R].
// We showcase two implementations - one using a recursive algorithm, and one using an iterative one.

// 2.2 Recursive Implementation
// class Solution {
//     int answerSum;
//     public void dtm(TreeNode node, int L, int R) {
//         if (node != null) {
//             if (L <= node.val && node.val <= R)
//                 answerSum += node.val;
//             if (L < node.val)
//                 dtm(node.left, L, R);
//             if (node.val < R)
//                 dtm(node.right, L, R);
//         }
//     }
//     public int rangeSumBST(TreeNode root, int L, int R) {
//         answerSum = 0;
//         dtm(root, L, R);  // do the method
//         return answerSum;
//     }
// }


// 2.3 Iterative Implementation
// class Solution {
//     public int rangeSumBST(TreeNode root, int L, int R) {
//         int ans = 0;
//         Stack<TreeNode> stack = new Stack();
//         stack.push(root);  // push root in 
//         while (!stack.isEmpty()) {
//             TreeNode node = stack.pop();
//             if (node != null) {
//                 if (L <= node.val && node.val <= R)
//                     ans += node.val;
//                 if (L < node.val)
//                     stack.push(node.left);
//                 if (node.val < R)
//                     stack.push(node.right);
//             }
//         }
//         return ans;
//     }
// }


// ------------------------------------------ mine:
// class Solution {
//     public int rangeSumBST(TreeNode root, int L, int R) {
//         int sum = 0;
//         int totalsum = 0;
//         if (root == null) {totalsum = 0;}
//         if (root != null) {
//             if ( L<=root.val && root.val<=R ) {
//                 sum = root.val;
//             }
//             totalsum = sum + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
//         }
//         return totalsum;
//     }
// }
// Runtime: 2 ms, faster than 26.56% of Java online submissions for Range Sum of BST.
// Memory Usage: 62.1 MB, less than 5.08% of Java online submissions for Range Sum of BST.


// class Solution {
//     public int rangeSumBST(TreeNode root, int L, int R) {
//         int sum = 0;
//         if (root == null) { return 0;}
//         if (root != null) {
//             if ( L<=root.val && root.val<=R ) {
//                 sum = root.val;
//             }
//             sum = sum + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
//         }
//         return sum;
//     }
// }
// Runtime: 1 ms, faster than 54.38% of Java online submissions for Range Sum of BST.
// Memory Usage: 47.1 MB, less than 76.50% of Java online submissions for Range Sum of BST.










// 