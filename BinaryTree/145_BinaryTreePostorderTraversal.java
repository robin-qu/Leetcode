/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// // Traversal
// class Solution {
//     public List<Integer> postorderTraversal(TreeNode root) {
//         List<Integer> res = new LinkedList<>();
//         helper(root, res);
//         return res;
//     }
    
//     private void helper(TreeNode root, List<Integer> list) {
//         if (root != null) {
//             helper(root.left, list);
//             helper(root.right, list);
//             list.add(root.val);
//         }
//     }
// }

// // Divide and Conquer
// class Solution {
//     public List<Integer> postorderTraversal(TreeNode root) {
//         List<Integer> res = new LinkedList<>();
        
//         if (root == null) {
//             return res;
//         }
        
//         List<Integer> leftList = postorderTraversal(root.left);
//         List<Integer> rightList = postorderTraversal(root.right);
        
//         res.addAll(leftList);
//         res.addAll(rightList);
//         res.add(root.val);
        
//         return res;
//     }
// }

// // Iterative version II
// class Solution {
//     public List<Integer> postorderTraversal(TreeNode root) {
//         List<Integer> res = new LinkedList<>();
//         Stack<TreeNode> stack = new Stack<>();
        
//         if (root == null) {
//             return res;
//         }
        
//         stack.push(root);
        
//         while (!stack.isEmpty()) {
//             TreeNode curr = stack.pop();
//             res.add(0, curr.val);
            
//             if (curr.left != null) {
//                 stack.push(curr.left);
//             }
//             if (curr.right != null) {
//                 stack.push(curr.right);
//             }
//         }
        
//         return res;
//     }
// }

// Iterative version II
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                res.add(0, curr.val);
                curr = curr.right;
            }
            
            curr = stack.pop();
            curr = curr.left;
        }
        
        return res;
    }
}
