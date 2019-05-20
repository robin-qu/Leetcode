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
//     public boolean hasPathSum(TreeNode root, int sum) {
//         if (root == null) {
//             return false;
//         }
        
//         if (root.left == null && root.right == null && root.val == sum) {
//             return true;
//         }
        
//         return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
//     }
// }

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        stack.push(root);
        sumStack.push(root.val);
        
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            int currSum = sumStack.pop();
            
            if (curr.left == null && curr.right == null && currSum == sum) {
                return true;
            }
            
            if (curr.left != null) {
                stack.push(curr.left);
                sumStack.push(curr.left.val + currSum);
            }
            
            if (curr.right != null) {
                stack.push(curr.right);
                sumStack.push(curr.right.val + currSum);
            }
        }
        
        return false;
    }
}