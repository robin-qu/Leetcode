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
//      * @return: the length of the longest consecutive sequence path
//      */
     
//     class ResultType {
//         public int currLen;
//         public int maxLen;
        
//         public ResultType(int currLen, int maxLen) {
//             this.currLen = currLen;
//             this.maxLen = maxLen;
//         }
//     }
    
//     public int longestConsecutive(TreeNode root) {
//         return helper(root).maxLen;
//     }
    
//     private ResultType helper(TreeNode root) {
//         if (root == null) {
//             return new ResultType(0, 0);
//         }
        
//         ResultType left = helper(root.left);
//         ResultType right = helper(root.right);
        
//         int currLen = 1;
//         if (root.left != null && root.val + 1 == root.left.val) {
//             currLen = Math.max(currLen, left.currLen + 1);
//         }
//         if (root.right != null && root.val + 1 == root.right.val) {
//             currLen = Math.max(currLen, right.currLen + 1);
//         }
        
//         int maxLen = Math.max(currLen, Math.max(left.maxLen, right.maxLen));
        
//         return new ResultType(currLen, maxLen);
//     }
// }


public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
     
    private int max;
    
    public int longestConsecutive(TreeNode root) {
        max = 0;
        helper(root);
        return max;
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = helper(root.left);
        int right = helper(root.right);
        
        int currLen = 1;
        if (root.left != null && root.val + 1 == root.left.val) {
            currLen = Math.max(currLen, left + 1);
        }
        if (root.right != null && root.val + 1 == root.right.val) {
            currLen = Math.max(currLen, right + 1);
        }
        
        max = Math.max(currLen, max);
        
        return currLen;
    }
}