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

// public class Solution {
//     /**
//      * @param root: the root of binary tree
//      * @return: the root of the minimum subtree
//      */
    
//     private TreeNode res;
//     private int minSum;
    
//     public TreeNode findSubtree(TreeNode root) {
//         res = root;
//         minSum = Integer.MAX_VALUE;
        
//         helper(root);
        
//         return res;
//     }
    
//     private int helper(TreeNode root) {
//         if (root == null) {
//             return 0;
//         }
        
//         int sum = root.val + helper(root.left) + helper(root.right);
//         if (sum < minSum) {
//             minSum = sum;
//             res = root;
//         }
        
//         return sum;
//     }
// }


public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the root of the minimum subtree
     */
     
    class ResultType {
        public int sum;
        public int minSum;
        public TreeNode root;
        
        public ResultType(int sum, int minSum, TreeNode root) {
            this.sum = sum;
            this.minSum = minSum;
            this.root = root;
        }
    }
    
    public TreeNode findSubtree(TreeNode root) {
        return helper(root).root;
    }
    
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, Integer.MAX_VALUE, null);
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        int sum = root.val + left.sum + right.sum;
        int minSum = sum;
        TreeNode minRoot = root;
        
        if (left.minSum < minSum) {
            minSum = left.minSum;
            minRoot = left.root;
        } 
        
        if (right.minSum < minSum) {
            minSum = right.minSum;
            minRoot = right.root;
        }
        
        return new ResultType(sum, minSum, minRoot);
    }
}