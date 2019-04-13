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
//     public TreeNode deleteNode(TreeNode root, int key) {
//         if (root == null) {
//             return null;
//         }
        
//         if (root.left == null && root.right == null && root.val == key) {
//             return null;
//         }
        
//         helper(root, null, key, root);
        
//         return root;
//     }
    
//     private TreeNode helper(TreeNode curr, TreeNode prev, int key, TreeNode root) { 
//         if (curr == null) {
//             return null;
//         }
        
//         if (curr.val > key) {
//             return helper(curr.left, curr, key, root);
//         }
        
//         if (curr.val < key) {
//             return helper(curr.right, curr, key, root);
//         }
        
//         TreeNode newNode = getMax(curr, prev);
//         newNode.right = curr.right;
//         newNode.left = curr.left;
        
//         if (prev == null) {
//             root = newNode;
//         } else {
//             if (prev.right == curr) {
//                 prev.right = newNode;
//             }
//             if (prev.left == curr) {
//                 prev.left = newNode;
//             }
//         }

//         return curr;
//     }
    
//     // Pre : curr is not null
//     private TreeNode getMax(TreeNode curr, TreeNode prev) {
//         while (curr.right != null) {
//             prev = curr;
//             curr = curr.right;
//         }
        
//         if (prev != null) {
//             prev.right = curr.left;            
//         }
//         return curr;
//     }
// } 

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode curr = root;
        TreeNode prev = null;
        
        while (curr != null && curr.val != key) {
            prev = curr;
            if (curr.val > key) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        
        if (prev == null) {
            return deleteRoot(curr);
        }
        
        if (prev.left == curr) {
            prev.left = deleteRoot(curr);
        }
        
        if (prev.right == curr) {
            prev.right = deleteRoot(curr);
        }
        
        return root;
    }
    
    private TreeNode deleteRoot(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        if (root.left == null) {
            return root.right;
        }
        
        if (root.right == null) {
            return root.left;
        }
        
        TreeNode prev = null;
        TreeNode next = root.right;
        
        while (next.left != null) {
            prev = next;
            next = next.left;
        }
        
        next.left = root.left;
        if (root.right != next) {
            prev.left = next.right;
            next.right = root.right;
        }
        
        return next;
    }
}