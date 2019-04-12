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

// // Divide and Conquer
// public class Solution {
//     /**
//      * @param root: param root: The root of the binary search tree
//      * @param k1: An integer
//      * @param k2: An integer
//      * @return: return: Return all keys that k1<=key<=k2 in ascending order
//      */
//     public List<Integer> searchRange(TreeNode root, int k1, int k2) {
//         List<Integer> res = new LinkedList<>();
        
//         if (root == null) {
//             return res;
//         }
        
//         List<Integer> left = searchRange(root.left, k1, k2);
//         List<Integer> right = searchRange(root.right, k1, k2);
        
//         if (root.val >= k1 && root.val <= k2) {
//             res.add(root.val);
//         }
        
//         for (int n : left) {
//             res.add(n);
//         }
        
//         for (int n : right) {
//             res.add(n);
//         }
        
//         return res;
        
//     }
// }


// Iterative
public class Solution {
    /**
     * @param root: param root: The root of the binary search tree
     * @param k1: An integer
     * @param k2: An integer
     * @return: return: Return all keys that k1<=key<=k2 in ascending order
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        
        if (root == null) {
            return res;
        }
        
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            
            if (curr.val >= k1 && curr.val <= k2) {
                res.add(curr.val);
            }
            
            if (curr.left != null) {
                stack.push(curr.left);
            }
            
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        
        return res;
    }
}