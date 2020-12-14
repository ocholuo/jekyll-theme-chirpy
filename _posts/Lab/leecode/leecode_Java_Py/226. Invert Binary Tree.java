


// 226. Invert Binary Tree
// https://leetcode.com/problems/invert-binary-tree/

// Example:
// Input:
//      4
//    /   \
//   2     7
//  / \   / \
// 1   3 6   9

// Output:
//      4
//    /   \
//   7     2
//  / \   / \
// 9   6 3   1

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

// ------------mine: new binary tree:
// class Solution {
//     public TreeNode invertTree(TreeNode root) {
//         TreeNode newtree = new TreeNode();
//         if (root == null){
//             return root;
//         }
//         newtree.val = root.val;
//         newtree.left = invertTree(root.right);
//         newtree.right = invertTree(root.left);
//         return newtree;
//     }
// }
// Runtime: 0 ms, faster than 100.00% of Java online submissions for Invert Binary Tree.
// Memory Usage: 36.5 MB, less than 92.70% of Java online submissions for Invert Binary Tree.


// Approach #1 (Recursive) [Accepted]
// class Solution {
//     public TreeNode invertTree(TreeNode root) {
//         if (root == null) {
//             return null;
//         }
//         TreeNode right = invertTree(root.right);
//         TreeNode left = invertTree(root.left);
//         root.left = right;
//         root.right = left;
//         return root;
//     }
// }
// Runtime: 0 ms, faster than 100.00% of Java online submissions for Invert Binary Tree.
// Memory Usage: 36.5 MB, less than 93.90% of Java online submissions for Invert Binary Tree.


// Approach #2 (Iterative) [Accepted]

// class Solution {
//     public TreeNode invertTree(TreeNode root) {
//         if (root == null) return null;
//         Queue<TreeNode> queue = new LinkedList<TreeNode>();
//         queue.add(root);
//         while (!queue.isEmpty()) {
//             TreeNode current = queue.poll();
//             TreeNode temp = current.left;
//             current.left = current.right;
//             current.right = temp;
//             if (current.left != null) queue.add(current.left);
//             if (current.right != null) queue.add(current.right);
//         }
//         return root;
//     }
// }



















// 