/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// // Recursively O(n)time O(n)space
// class Solution {
//     public boolean isSymmetric(TreeNode root) {
//         if (root == null) {
//             return true;
//         }
        
//         return helper(root.left, root.right);
//     }
    
//     private boolean helper(TreeNode left, TreeNode right) {
//         if (left == null && right == null) {
//             return true;
//         }
        
//         if (left == null || right == null) {
//             return false;
//         }
        
//         if (left.val != right.val) {
//             return false;
//         }
        
//         return helper(left.left, right.right) && helper(left.right, right.left);
//     }
// }


// // Iteratively O(n)time O(n)space
// class Solution {
//     public boolean isSymmetric(TreeNode root) {
//         if (root == null) {
//             return true;
//         }
        
//         Queue<TreeNode> queue = new LinkedList<>();
//         queue.offer(root);
        
//         while (!queue.isEmpty()) {
//             int size = queue.size();
//             Stack<String> stack = new Stack<>();
            
//             for (int i = 0; i < size; i++) {
//                 TreeNode curr = queue.poll();
                
//                 if (curr != null) {
//                     queue.offer(curr.left);
//                     queue.offer(curr.right);
//                 }
                
//                 if (size == 1) {
//                     continue;
//                 }
                
//                 String val = curr == null ? "null" : String.valueOf(curr.val);
//                 if (i < size / 2) {
//                     stack.push(val);
//                 } else {
//                     if (stack.isEmpty() || !val.equals(stack.pop())) {
//                         return false;
//                     }
//                 }
//             }
//         }
        
//         return true;
//     }
// }


// Iteratively O(n)time O(n)space
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            
            if (t1 == null && t2 == null) {
                continue;
            }
            
            if (t1 == null || t2 == null) {
                return false;
            }
            
            if (t1.val != t2.val) {
                return false;
            }
            
            queue.offer(t1.left);
            queue.offer(t2.right);
            queue.offer(t1.right);
            queue.offer(t2.left);
        }
        
        return true;
    }
} 