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
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        
        return countFromRoot(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int countFromRoot(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        
        int left = countFromRoot(root.left, sum - root.val);
        int right = countFromRoot(root.right, sum - root.val);
        
        return left + right + (root.val == sum ? 1 : 0);
    }
}