/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


// public class Solution {
//     /*
//      * @param root: The root of the BST.
//      * @param p: You need find the successor node of p.
//      * @return: Successor of p.
//      */
//     public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
//         if (root == null) {
//             return null;
//         }
        
//         Stack<TreeNode> stack = new Stack<>();
//         TreeNode curr = root;
        
//         while (curr != null || !stack.isEmpty()) {
//             while (curr != null) {
//                 stack.push(curr);
//                 curr = curr.left;
//             }
            
//             curr = stack.pop();
//             if (curr.val > p.val) {
//                 return curr;
//             }
//             curr = curr.right;
//         }
        
//         return null;
//     }
// }


public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        
        while (root != null) {
            if (root.val <= p.val) {
                root = root.right;
            } else {
                res = root;
                root = root.left;
            }
        }
        
        return res;
    }
}


// public class Solution {
//     public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
//         if (root == null || p == null) {
//             return null;
//         }
        
//         if (root.val <= p.val) {   // goes to right branch
//             return inorderSuccessor(root.right, p);
//         } else {   // goes to left branch
//             TreeNode left = inorderSuccessor(root.left, p);
//             return left == null ? root : left;
//         }
//     }
// }