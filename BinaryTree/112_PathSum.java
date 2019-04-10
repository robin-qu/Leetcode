/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// // Divide and Conquer
// class Solution {
//     public boolean hasPathSum(TreeNode root, int sum) {
//         if (root == null) {
//             return false;
//         }
        
//         if (root.left == null && root.right == null) {
//             return root.val == sum;
//         }
        
//         return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
//     }
// }

// Iterative
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> sums = new Stack<>();
        
        if (root == null) {
            return false;
        }
        
        stack.push(root);
        sums.push(root.val);
        
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            int currSum = sums.pop();
            
            if (curr.left == null && curr.right == null && currSum == sum) {
                return true;
            }
            
            if (curr.left != null) {
                stack.push(curr.left);
                sums.push(currSum + curr.left.val);
            }
            
            if (curr.right != null) {
                stack.push(curr.right);
                sums.push(currSum + curr.right.val);
            }
        }
        
        return false;
    }
}