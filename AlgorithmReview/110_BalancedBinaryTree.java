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
//     class ResultType {
//         public int depth;
//         public boolean isBalanced;
        
//         public ResultType(int depth, boolean isBalanced) {
//             this.depth = depth;
//             this.isBalanced = isBalanced;
//         }
//     }
    
//     public boolean isBalanced(TreeNode root) {
//         return helper(root).isBalanced;
//     }
    
//     private ResultType helper(TreeNode root) {
//         if (root == null) {
//             return new ResultType(0, true);
//         }
        
//         ResultType left = helper(root.left);
//         ResultType right = helper(root.right);
        
//         int depth = 1 + Math.max(left.depth, right.depth);
//         boolean isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.depth - right.depth) <= 1;
        
//         return new ResultType(depth, isBalanced);
//     }
// }


class Solution {    
    public boolean isBalanced(TreeNode root) {
        return helper(root) != -1;
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = helper(root.left);
        int right = helper(root.right);
        
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        
        return 1 + Math.max(left, right);
    }
}