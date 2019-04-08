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
 
class ResultType {
    public int sum;
    public int count;
    public int sumMax;
    public int countMax;
    public TreeNode node;
    
    public ResultType(TreeNode node, int sum, int count, int sumMax, int countMax) {
        this.node = node;
        this.sum = sum;
        this.count = count;
        this.sumMax = sumMax;
        this.countMax = countMax;
    }
    
    public ResultType() {
        this.node = null;
        this.sum = 0;
        this.count = 0;
        this.sumMax = 0;
        this.countMax = 0;
    }
}

public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the root of the maximum average of subtree
     */
    public TreeNode findSubtree2(TreeNode root) {
        return helper(root).node;
    }
    
    public ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType();
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        ResultType curr = new ResultType(root,
                                root.val + left.sum + right.sum,
                                1 + left.count + right.count,
                                root.val + left.sum + right.sum,
                                1 + left.count + right.count);
                                
        if (curr.sumMax * left.countMax < left.sumMax * curr.countMax) {
            curr.sumMax = left.sumMax;
            curr.countMax = left.countMax;
            curr.node = left.node;
        }
        if (curr.sumMax * right.countMax < right.sumMax * curr.countMax) {
            curr.sumMax = right.sumMax;
            curr.countMax = right.countMax;
            curr.node = right.node;
        }
        
        return curr;
    }
}