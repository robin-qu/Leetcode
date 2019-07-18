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
//      * @param root: a TreeNode, the root of the binary tree
//      * @return: nothing
//      */
//     public void flatten(TreeNode root) {
//         if (root == null) {
//             return;
//         }
        
//         helper(root);
//     }
    
//     private TreeNode helper(TreeNode root) {
//         if (root == null) {
//             return null;
//         }
        
//         TreeNode left = helper(root.left);
//         TreeNode right = helper(root.right);
        
//         if (left == null && right == null) {
//             return root;
//         }
        
//         if (left == null) {
//             return right;
//         }
        
//         if (right == null) {
//             root.right = root.left;
//             root.left = null;
//             return left;
//         }
        
//         TreeNode temp = root.right;
//         root.right = root.left;
//         root.left = null;
//         left.right = temp;
        
//         return right;
//     }
// }


// // Stack Iterative (initial version)
// public class Solution {
//     /**
//      * @param root: a TreeNode, the root of the binary tree
//      * @return: nothing
//      */
//     public void flatten(TreeNode root) {
//         if (root == null) {
//             return;
//         }
        
//         Stack<TreeNode> stack = new Stack<>();
//         stack.push(root);
        
//         while (!stack.isEmpty()) {
//             TreeNode curr = stack.pop();
            
//             if (curr.right != null) {
//                 stack.push(curr.right);
//             }
            
//             if (curr.left != null) {
//                 stack.push(curr.left);
//                 curr.right = curr.left;
//                 curr.left = null;
//             }
            
//             if (curr.left == null && curr.right == null && !stack.isEmpty()) {
//                 curr.right = stack.peek();
//             }
//         }
//     }
// }


// Stack Iterative (initial version)
public class Solution {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            
            if (curr.right != null) {
                stack.push(curr.right);
            }
            
            if (curr.left != null) {
                stack.push(curr.left);
            }
            
            // connect
            curr.left = null;
            if (stack.isEmpty()) {
                curr.right = null;
            } else {
                curr.right = stack.peek();
            }
        }
    }
}