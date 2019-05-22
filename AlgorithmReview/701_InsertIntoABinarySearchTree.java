/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// class Solution {
//     public TreeNode insertIntoBST(TreeNode root, int val) {
//         if (root == null) {
//             return new TreeNode(val);
//         }
        
//         TreeNode prev = null;
//         TreeNode curr = root;
        
//         while (curr != null) {
//             prev = curr;
//             if (val < curr.val) {
//                 curr = curr.left;
//             } else {
//                 curr = curr.right;
//             }
//         }
        
//         TreeNode newNode = new TreeNode(val);
        
//         if (prev.val < val) {
//             prev.right = newNode;
//         } else {
//             prev.left = newNode;
//         }
        
//         return root;
//     }
// }

// class Solution {
//     public TreeNode insertIntoBST(TreeNode root, int val) {
//         if (root == null) {
//             return new TreeNode(val);
//         }
        
//         TreeNode curr = root;
        
//         while (curr != null) {
//             if (val < curr.val) {
//                 if (curr.left == null) {
//                     curr.left = new TreeNode(val);
//                     break;
//                 }
//                 curr = curr.left;
//             } else {
//                 if (curr.right == null) {
//                     curr.right = new TreeNode(val);
//                     break;
//                 }
//                 curr = curr.right;
//             }
//         }
        
//         return root;
//     }
// }


class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        
        return root;
    }
}