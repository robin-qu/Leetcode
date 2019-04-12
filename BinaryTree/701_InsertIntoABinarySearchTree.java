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
//     public TreeNode insertIntoBST(TreeNode root, int val) {
//         helper(root, val);
//         return root;
//     }
    
//     private void helper(TreeNode root, int val) {
//         if (root == null) {
//             return;
//         }
        
//         if (root.val > val) {
//             if (root.left == null) {
//                 root.left = new TreeNode(val);
//             } else {
//                 helper(root.left, val);
//             }
//         }
        
//         if (root.val < val) {
//             if (root.right == null) {
//                 root.right = new TreeNode(val);
//             } else {
//                 helper(root.right, val);
//             }
//         }
//     }
// }

// Recursive
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return root;
        }
        
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val < val) {
                if (curr.right == null) {
                    curr.right = new TreeNode(val);
                    break;
                } else {
                    curr = curr.right;
                }
            }
            
            if (curr.val > val) {
                if (curr.left == null) {
                    curr.left = new TreeNode(val);
                    break;
                } else {
                    curr = curr.left;
                }
            }
        }
        
        return root;
    }
}