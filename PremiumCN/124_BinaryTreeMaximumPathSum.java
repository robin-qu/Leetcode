/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        
        int leftMax = maxPathSum(root.left);
        int rightMax = maxPathSum(root.right);
        
        int leftFromRoot = Math.max(0, maxFromRoot(root.left));
        int rightFromRoot = Math.max(0, maxFromRoot(root.right));
        int curr = root.val + leftFromRoot + rightFromRoot;
        
        return Math.max(curr, Math.max(leftMax, rightMax));
    }
                                        
    private int maxFromRoot(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        
        int left = Math.max(0, maxFromRoot(root.left));
        int right = Math.max(0, maxFromRoot(root.right));
        
        return root.val + Math.max(left, right);
    }
}