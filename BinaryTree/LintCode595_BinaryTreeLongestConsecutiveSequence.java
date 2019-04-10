/**
 * Given a binary tree, find the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node to any node 
 * in the tree along the parent-child connections. The longest consecutive path 
 * need to be from parent to child (cannot be the reverse).
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

// // Traverse
// public class Solution {
//     /**
//      * @param root: the root of binary tree
//      * @return: the length of the longest consecutive sequence path
//      */
//     private int longest;
     
//     public int longestConsecutive(TreeNode root) {
//         longest = 0;
//         helper(root);
//         return longest;
//     }
    
//     // Return the number of longest consecutive sequence starting from root
//     private int helper(TreeNode root) {
//         if (root == null) {
//             return 0;
//         }
        
//         int left = helper(root.left);
//         int right = helper(root.right);
        
//         int len = 1;
//         if (root.left != null && root.val + 1 == root.left.val) {
//             len = Math.max(left + 1, len);
//         }
        
//         if (root.right != null && root.val + 1 == root.right.val) {
//             len = Math.max(right + 1, len);
//         }
        
//         longest = Math.max(longest, len);
        
//         return len;
//     }
// }


// Divide and Conquer
public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    class ResultType {
        public int len;
        public int longest;
         
        public ResultType(int len, int longest) {
            this.len = len;
            this.longest = longest;
        }
    }
     
    public int longestConsecutive(TreeNode root) {
        return helper(root).longest;
    }
    
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0);
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        ResultType curr = new ResultType(1, 0);
        
        if (root.left != null && root.val + 1 == root.left.val) {
            curr.len = Math.max(left.len + 1, curr.len);
        }
        
        if (root.right != null && root.val + 1 == root.right.val) {
            curr.len = Math.max(right.len + 1, curr.len);
        }
        
        curr.longest = Math.max(curr.len, Math.max(left.longest, right.longest));
        
        return curr;
    }
}