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
//     public List<Integer> postorderTraversal(TreeNode root) {
//         List<Integer> res = new ArrayList<>();
        
//         helper(root, res);
//         return res;
//     }
    
//     private void helper(TreeNode root, List<Integer> res) {
//         if (root == null) {
//             return;
//         }
        
//         helper(root.left, res);
//         helper(root.right, res);
//         res.add(root.val);
//     }
// }

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.add(0, curr.val);
            
            if (curr.left != null) {
                stack.add(curr.left);
            }
            
            if (curr.right != null) {
                stack.add(curr.right);
            }
        }
        
        return res;
    }
}