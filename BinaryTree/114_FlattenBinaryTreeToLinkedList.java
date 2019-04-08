/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// // Traversal
// public class Solution {
//     private TreeNode lastNode = null;

//     public void flatten(TreeNode root) {
//         if (root == null) {
//             return;
//         }

//         if (lastNode != null) {
//             lastNode.left = null;
//             lastNode.right = root;
//         }

//         lastNode = root;
//         TreeNode right = root.right;
//         flatten(root.left);
//         flatten(right);
//     }
// }


// // Divide and Conquer
// class Solution {
//     public void flatten(TreeNode root) {
//         helper(root);
//     }
    
//     private TreeNode helper(TreeNode node) {
//         if (node == null) {
//             return null;
//         }
        
//         TreeNode left = helper(node.left);
//         TreeNode right = helper(node.right);
        
//         if (left != null) {
//             left.right = node.right;
//             node.right = node.left;
//             node.left = null;
//         }
        
//         if (right != null) {
//             return right;
//         }
        
//         if (left != null) {
//             return left;
//         }
        
//         return node;
//     }
// }

// DFS Iterative
public class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            
            if (curr.right != null) {
                stack.push(curr.right);
            }
            
            if (curr.left != null) {
                stack.push(curr.left);
            }
            
            curr.left = null;
            curr.right = stack.isEmpty() ? null : stack.peek();
        }
    }
}