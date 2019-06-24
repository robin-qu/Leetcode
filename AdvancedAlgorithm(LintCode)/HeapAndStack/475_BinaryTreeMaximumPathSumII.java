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

// // recursive
// public class Solution {
//     /**
//      * @param root: the root of binary tree.
//      * @return: An integer
//      */
//     private int max = Integer.MIN_VALUE;
    
//     public int maxPathSum2(TreeNode root) {
//         if (root == null) {
//             return max;
//         }
        
//         traverse(root, root.val);
        
//         return max;
//     }
    
//     private void traverse(TreeNode root, int val) {
//         this.max = Math.max(max, val);
        
//         if (root.left != null) {
//             traverse(root.left, val + root.left.val);
//         }
        
//         if (root.right != null) {
//             traverse(root.right, val + root.right.val);
//         }
//     }
// }


// nonrecursion
public class Solution {
    /**
     * @param root: the root of binary tree.
     * @return: An integer
     */
    public int maxPathSum2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Stack<TreeNode> nodes = new Stack<>();
        Stack<Integer> sums = new Stack<>();
        int max = Integer.MIN_VALUE;
        
        nodes.push(root);
        sums.push(root.val);
        
        while (!nodes.isEmpty()) {
            TreeNode currNode = nodes.pop();
            int currSum = sums.pop();
            
            max = Math.max(currSum, max);
            
            if (currNode.left != null) {
                nodes.push(currNode.left);
                sums.push(currSum + currNode.left.val);
            }
            
            if (currNode.right != null) {
                nodes.push(currNode.right);
                sums.push(currSum + currNode.right.val);
            }
        }
        
        return max;
    }
}