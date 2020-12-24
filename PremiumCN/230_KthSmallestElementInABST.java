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
    public int kthSmallest(TreeNode root, int k) {
        while (root != null) {
            int count = 1 + getCount(root.left);
            if (count < k) {
                root = root.right;
                k -= count;
            } else if (count > k) {
                root = root.left;
            } else {
                return root.val;
            }
        }
        return 0;
    }

    private int getCount(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + getCount(root.left) + getCount(root.right);
    }
}