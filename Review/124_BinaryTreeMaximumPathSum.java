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
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxFromRoot(root);
        return max;
    }

    private int maxFromRoot(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(0, maxFromRoot(root.left));
        int right = Math.max(0, maxFromRoot(root.right));

        int sum = root.val + Math.max(left, right);
        int currSum = root.val + left + right;
        max = Math.max(currSum, max);
        return sum;
    }
}