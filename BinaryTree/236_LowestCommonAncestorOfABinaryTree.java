/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Divide and Conquer
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) {
            return root;
        }
        
        if (left != null) {
            return left;
        }
        
        if (right != null) {
            return right;
        }
        
        return null;
    }
}

// // DFS Iterative
// class Solution {
//     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//         Stack<TreeNode> stack = new Stack<>();
        
//         if (root == null) {
//             return null;
//         }
        
//         stack.push(root);
        
//         while (!stack.isEmpty()) {
//             TreeNode curr = stack.pop();
                
//             if (curr == p
//                 || curr == q 
//                 || exist(curr.left, p) && exist(curr.right, q) 
//                 || exist(curr.left, q) && exist(curr.right, p)) {
//                 return curr;
//             }
            
//             if (curr.left != null) {
//                 stack.push(curr.left);
//             }
            
//             if (curr.right != null) {
//                 stack.push(curr.right);
//             }
//         }
        
//         return null;
//     }
    
//     // Returns true if node exists in the tree root
//     private boolean exist(TreeNode root, TreeNode node) {
//         if (root == null) {
//             return false;
//         }
        
//         return (root == node)
//                 || exist(root.left, node)
//                 || exist(root.right, node);
//     }
// }