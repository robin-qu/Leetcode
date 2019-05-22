// /**
//  * Definition for a binary tree node.
//  * public class TreeNode {
//  *     int val;
//  *     TreeNode left;
//  *     TreeNode right;
//  *     TreeNode(int x) { val = x; }
//  * }
//  */
// class Solution {
//     public TreeNode deleteNode(TreeNode root, int key) {
//         if (root == null) {
//             return null;
//         }
        
//         TreeNode prev = null;
//         TreeNode curr = root;
        
//         while (curr != null && curr.val != key) {
//             prev = curr;
//             if (curr.val < key) {
//                 curr = curr.right;
//             } else {
//                 curr = curr.left;
//             }
//         }
        
//         if (curr == null) {  // not found, return original root
//             return root;
//         }
        
//         // curr is the node to be removed
//         // finds the smallest value in the right subtree
//         TreeNode newNode = remove(curr.right);
//         if (newNode != null) {
//             if (newNode == curr.right) {
//                 newNode.left = curr.left;
//             } else {
//                 newNode.left = curr.left;
//                 newNode.right = curr.right;
//             }
//         } else {
//             newNode = curr.left;
//         }
            
        
//         if (prev == null) {
//             root = newNode;
//         } else if (prev.val < curr.val) {
//             prev.right = newNode;
//         } else {
//             prev.left = newNode;
//         }
        
//         return root;
//     }
    
//     // removes and returns the smallest node
//     private TreeNode remove(TreeNode root) {
//         TreeNode prev = null;
//         TreeNode curr = root;
//         if (curr == null) {
//             return null;
//         }
        
//         while (curr.left != null) {
//             prev = curr;
//             curr = curr.left;
//         }
        
//         TreeNode res = curr;
//         if (prev != null) {
//             prev.left = curr.right;
//         }
//         return res;
//     }
// }

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        
        TreeNode prev = null;
        TreeNode curr = root;
        
        // find the target node
        while (curr != null && curr.val != key) {
            prev = curr;
            if (curr.val < key) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        
        if (prev == null) {
             return remove(curr, key);
        }
        
        if (prev.left == curr) {
            prev.left = remove(curr, key);
        }
        
        if (prev.right == curr) {
            prev.right = remove(curr, key);
        }
        
        return root;
    }
    
    // return the root of updated subtree
    private TreeNode remove(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        
        if (root.left == null) {
            return root.right;
        }
        
        if (root.right == null) {
            return root.left;
        }
        
        TreeNode newRoot = root.right;
        TreeNode prev = root;
        
        while (newRoot.left != null) {
            prev = newRoot;
            newRoot = newRoot.left;
        }
        
        if (root.right != newRoot) {
            prev.left = newRoot.right;
            newRoot.right = root.right;
        }
        newRoot.left = root.left;
        
        return newRoot;
    }
}