/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// // Travesal
// class Solution {
//     public List<Integer> inorderTraversal(TreeNode root) {
//         List<Integer> res = new LinkedList<>();
//         helper(root, res);
//         return res;
//     }
    
//     private void helper(TreeNode root, List<Integer> list) {
//         if (root != null) {
//             helper(root.left, list);
//             list.add(root.val);
//             helper(root.right, list);
//         }
//     }
// }

// // Divide and Conquer
// class Solution {
//     public List<Integer> inorderTraversal(TreeNode root) {
//         List<Integer> res = new LinkedList<>();
        
//         if (root == null) {
//             return res;
//         }
        
//         List<Integer> leftList = inorderTraversal(root.left);
//         List<Integer> rightList = inorderTraversal(root.right);
         
//         res.addAll(leftList);
//         res.add(root.val);
//         res.addAll(rightList);
        
//         return res;
//     }
// }


// Iterative
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        
        return res;
    }
}