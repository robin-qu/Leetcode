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
     * @return: the root of the maximum average of subtree
     */
     
    class ResultType {
        public int currSum;
        public int currCount;
        public int maxSum;
        public int maxCount;
        public TreeNode root;
        
        public ResultType (int currSum, int currCount, int maxSum, int maxCount, TreeNode root) {
            this.currSum = currSum;
            this.currCount = currCount;
            this.maxSum = maxSum;
            this.maxCount = maxCount;
            this.root = root;
        }
    }
    
    public TreeNode findSubtree2(TreeNode root) {
        return helper(root).root;
    }
    
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0, 0, 0, null);
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        int currSum = root.val + left.currSum + right.currSum;
        int currCount = 1 + left.currCount + right.currCount;
        int maxSum = currSum;
        int maxCount = currCount;
        TreeNode maxNode = root;
        
        if (left.maxSum * maxCount > maxSum * left.maxCount) {
            maxSum = left.maxSum;
            maxCount = left.maxCount;
            maxNode = left.root;
        }
        
        if (right.maxSum * maxCount > maxSum * right.maxCount) {
            maxSum = right.maxSum;
            maxCount = right.maxCount;
            maxNode = right.root;
        }
        
        return new ResultType(currSum, currCount, maxSum, maxCount, maxNode);
    }
}