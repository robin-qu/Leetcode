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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, 1, res);
        return res;
    }

    private void helper(TreeNode root, int level, List<Integer> res) {
        if (root == null) {
            return;
        }

        if (res.size() < level) {
            res.add(root.val);
        } else {
            res.set(level - 1, root.val);
        }

        helper(root.left, level + 1, res);
        helper(root.right, level + 1, res);
    }
}