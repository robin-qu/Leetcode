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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }

        TreeNode curr = root;
        TreeNode prev = null;
        while (curr != null) {
            if (curr.val < p.val) {
                curr = curr.right;
            } else if (curr.val > p.val) {
                prev = curr;
                curr = curr.left;
            } else {
                curr = curr.right;
                if (curr == null) {
                    return prev;
                }
                while (curr.left != null) {
                    curr = curr.left;
                }
                return curr;
            }
        }

        return prev;
    }
}