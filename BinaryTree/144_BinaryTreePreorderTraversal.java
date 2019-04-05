/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// // Traverse
// class Solution {
//     public List<Integer> preorderTraversal(TreeNode root) {
//         List<Integer> res = new LinkedList<>();
//         helper(root, res);
//         return res;
//     }
    
//     private void helper(TreeNode root, List<Integer> list) {
//         if (root != null) {
//             list.add(root.val);
//             helper(root.left, list);
//             helper(root.right, list);
//         }
//     }
// }

// // Divide and Conquer
// class Solution {
//     public List<Integer> preorderTraversal(TreeNode root) {
//         List<Integer> res = new LinkedList<>();
        
//         if (root == null) {
//             return res;
//         }
        
//         List<Integer> leftList = preorderTraversal(root.left);
//         List<Integer> rightList = preorderTraversal(root.right);
        
//         res.add(root.val);
//         res.addAll(leftList);
//         res.addAll(rightList);
//         return res;
//     }
// }

// Iterative
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        List<Integer> res = new LinkedList<>();
        
        if (root == null) {
            return res;
        }
        
        s.push(root);
        
        while (!s.isEmpty()) {
            TreeNode curr = s.pop();
            res.add(curr.val);
            
            if (curr.right != null) {
                s.push(curr.right);
            }
            if (curr.left != null) {
                s.push(curr.left);
            }
        }
        
        return res;        
    }
}