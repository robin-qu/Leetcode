/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// // Nonrecursion O(n)time O(n)space
// class Solution {
//     public boolean isValidBST(TreeNode root) {
//         if (root == null) {
//             return true;
//         }
        
//         TreeNode curr = root;
//         Stack<TreeNode> stack = new Stack<>();
//         Stack<Integer> vals = new Stack<>();
        
//         while (curr != null || !stack.isEmpty()) {
//             while (curr != null) {
//                 stack.push(curr);
//                 curr = curr.left;
//             }
            
//             curr = stack.pop();
//             if (!vals.isEmpty() && curr.val <= vals.peek()) {
//                 return false;
//             }
//             vals.push(curr.val);
//             curr = curr.right;
//         }
        
//         return true;
//     }
// }


// Recursion O(n)time O(n)space
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean helper(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        
        if (root.val <= min || root.val >= max) {
            return false;
        }
        
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }
}