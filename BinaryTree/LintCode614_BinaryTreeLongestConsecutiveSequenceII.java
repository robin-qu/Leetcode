/**
 * Given a binary tree, find the length(number of nodes) of the longest consecutive sequence path.
 * The path could be start and end at any node in the tree
 *  
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
        public int longest;
        
        public ResultType(int up, int down, int longest) {
            this.up = up;
            this.down = down;
            this.longest = longest;
        }
    }
    
    public int longestConsecutive2(TreeNode root) {
        return helper(root).longest;
    }
    
    private ResultType helper(TreeNode node) {
        if (node == null) {
            return new ResultType(0, 0, 0);
        }
        
        ResultType left = helper(node.left);
        ResultType right = helper(node.right);
        ResultType curr = new ResultType(0, 0, 0);
        
        if (node.left != null) {
            if (node.val - 1 == node.left.val) {
                curr.down = Math.max(curr.down, left.down + 1);
            }
            
            if (node.val + 1 == node.left.val) {
                curr.up = Math.max(curr.up, left.up + 1);
            }
        }
        
        if (node.right != null) {
            if (node.val - 1 == node.right.val) {
                curr.down = Math.max(curr.down, right.down + 1);
            }
            
            if (node.val + 1 == node.right.val) {
                curr.up = Math.max(curr.up, right.up + 1);
            }
        }
        
        curr.longest = Math.max(1 + curr.up + curr.down, 
                            Math.max(left.longest, right.longest));
        
        return curr;
    }
}