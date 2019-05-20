/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
     
    class ResultType {
        public int up;
        public int down;
        public int max;
        
        public ResultType(int up, int down, int max) {
            this.up = up;
            this.down = down;
            this.max = max;
        }
    }
    
    public int longestConsecutive2(TreeNode root) {
        return helper(root).max;
    }
    
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0, 0);
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        int up = 1;
        int down = 1;
        
        if (root.left != null) {
            if (root.val + 1 == root.left.val) {
                up = Math.max(up, left.up + 1);
            } else if (root.val - 1 == root.left.val) {
                down = Math.max(down, left.down + 1);
            }
        }
        
        if (root.right != null) {
            if (root.val + 1 == root.right.val) {
                up = Math.max(up, right.up + 1);
            } else if (root.val - 1 == root.right.val) {
                down = Math.max(down, right.down + 1);
            }
        }
        
        int max = Math.max(up + down - 1, Math.max(left.max, right.max));
        
        return new ResultType(up, down, max);
    }
}