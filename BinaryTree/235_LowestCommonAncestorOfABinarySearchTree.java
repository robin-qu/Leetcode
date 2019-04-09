/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// // Recursive
// class Solution {
//     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//         if (root == null) {
//             return null;
//         }
        
//         if (root.val < Math.min(p.val, q.val)) {
//             return lowestCommonAncestor(root.right, p, q);
//         }
        
//         if (root.val > Math.max(p.val, q.val)) {
//             return lowestCommonAncestor(root.left, p, q);            
//         }
        
//         return root;
//     }
// }

// Iterative
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.val < Math.min(p.val, q.val)) {
                root = root.right;
            } else if (root.val > Math.max(p.val, q.val)) {
                root = root.left;
            } else {
                return root;
            }
        }
        
        return root;
    }
}