/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// O(n)time O(n)space
class Solution {
    class Pair {
        public int size;
        public int min;
        public int max;
        
        public Pair(int size, int min, int max) {
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }
    
    public int largestBSTSubtree(TreeNode root) {
        return helper(root).size;
    }
    
    private Pair helper(TreeNode root) {
        if (root == null) {
            return new Pair(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        
        Pair left = helper(root.left);
        Pair right = helper(root.right);
        
        if (root.val > left.max && root.val < right.min) {
            return new Pair(1 + left.size + right.size, Math.min(left.min, root.val), Math.max(right.max, root.val));
        }
            
        return new Pair(Math.max(left.size, right.size), Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}