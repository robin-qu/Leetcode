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
//     public void flatten(TreeNode root) {
//         helper(root);
//     }
    
//     private TreeNode helper(TreeNode root) {
//         if (root == null) {
//             return null;
//         }
        
//         TreeNode leftLast = helper(root.left);
//         TreeNode rightLast = helper(root.right);
        
//         if (leftLast != null) {
//             leftLast.right = root.right;
//             root.right = root.left;
//             root.left = null;
//         }
        
//         if (rightLast != null) {
//             return rightLast;
//         }
        
//         if (leftLast != null) {
//             return leftLast;
//         }
        
//         return root;
//     }
// }

class Solution {
    private TreeNode prev = null;
    
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        
        if (prev != null) {
            prev.left = null;
            prev.right = root;
        }
        
        prev = root;
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    }
}

// class Solution {
//     public void flatten(TreeNode root) {
//         if (root == null) {
//             return;
//         }
        
//         Stack<TreeNode> stack = new Stack<>();
//         stack.push(root);
        
//         while (!stack.isEmpty()) {
//             TreeNode curr = stack.pop();
            
//             if (curr.right != null) {
//                 stack.push(curr.right);
//             }
            
//             if (curr.left != null) {
//                 stack.push(curr.left);
//             }

//             curr.left = null;
//             curr.right = stack.isEmpty() ? null : stack.peek();
//         }
        
//     }
// }