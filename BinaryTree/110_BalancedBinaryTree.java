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
class ResultType {
    public int height;
    public boolean isBalanced;
    
    public ResultType(int height, boolean isBalanced) {
        this.height = height;
        this.isBalanced = isBalanced;
    }
}

class Solution {
    public boolean isBalanced(TreeNode root) {
        return helper(root).isBalanced;   
    }
    
    public ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, true);
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        boolean isBalanced = left.isBalanced && 
                    right.isBalanced && 
                    Math.abs(left.height - right.height) <= 1;
        
        return new ResultType(1 + Math.max(left.height, right.height), isBalanced);
    }
}

// // Traversal
// class Solution {
//     public boolean isBalanced(TreeNode root) {
//         if (root == null) {
//             return true;
//         }
        
//         boolean condition1 = Math.abs(getHeight(root.left) - getHeight(root.right)) < 2;
//         boolean condition2 = isBalanced(root.left);
//         boolean condition3 = isBalanced(root.right);
        
//         return condition1 && condition2 && condition3;
//     }
    
//     private int getHeight(TreeNode node) {
//         if (node == null) {
//             return 0;
//         }
        
//         return 1 + Math.max(getHeight(node.left), getHeight(node.right));
//     }
// }

// // DFS
// class Solution {
//     public boolean isBalanced(TreeNode root) {
//         return getHeight(root) != -1;
//     }
    
//     private int getHeight(TreeNode node) {
//         if (node == null) {
//             return 0;
//         }
        
//         int left = getHeight(node.left);
//         int right = getHeight(node.right);
        
//         if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
//             return -1;
//         }
        
//         return 1 + Math.max(left, right);
//     }
// }
