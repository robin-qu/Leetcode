/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// class Solution {
//     class ResultType {
//         public boolean isBST;
//         public long lowerBound;
//         public long upperBound;
        
//         public ResultType(boolean isBST, long lowerBound, long upperBound) {
//             this.isBST = isBST;
//             this.lowerBound = lowerBound;
//             this.upperBound = upperBound;
//         }
//     }
    
//     public boolean isValidBST(TreeNode root) {
//         return helper(root).isBST;
//     }
    
//     private ResultType helper(TreeNode root) {
//         if (root == null) {
//             return new ResultType(true, Long.MAX_VALUE, Long.MIN_VALUE);
//         }
        
//         ResultType left = helper(root.left);
//         ResultType right = helper(root.right);
        
//         boolean isBST = root.val > left.upperBound && 
//                         root.val < right.lowerBound &&
//                         left.isBST && right.isBST;
        
//         long lowerBound = Math.min(left.lowerBound, root.val);
//         long upperBound = Math.max(right.upperBound, root.val);
        
//         return new ResultType(isBST, lowerBound, upperBound);
//     }
// }

class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean helper(TreeNode root, long lb, long ub) {
        if (root == null) {
            return true;
        }
        
        if (root.val <= lb || root.val >= ub) {
            return false;
        }
        
        return helper(root.left, lb, root.val) && helper(root.right, root.val, ub);
    }
}