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
        if (root == null || p == null) {
            return null;
        }
        
        TreeNode prev = null;
        TreeNode curr = root;
        while (curr != null) {
            if (p.val < curr.val) {
                prev = curr;
                curr = curr.left;
            } else if (p.val > curr.val) {
                curr = curr.right;
            } else {
                if (curr.right != null) {
                    curr = curr.right;
                    while (curr.left != null) {
                        curr = curr.left;
                    }
                    return curr;
                }
                return prev;
            }
        }

        return null;
    }
}