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
//     public int maxDepth(TreeNode root) {
//         return helper(root, 0);
//     }
    
//     private int helper(TreeNode root, int d) {
//         if (root == null) {
//             return d;
//         }
        
//         return Math.max(helper(root.left, d + 1), helper(root.right, d + 1));
//     }
// }

// // Divide and Conquer
// class Solution {
//     public int maxDepth(TreeNode root) {
//         if (root == null) {
//             return 0;
//         }
        
//         return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
//     }
// }

// // DFS Iterative
// class Solution {
//     public int maxDepth(TreeNode root) {
//         int res = 0;
//         Stack<TreeNode> nodeStack = new Stack<>();
//         Stack<Integer> depthStack = new Stack<>();
        
//         if (root == null) {
//             return res;
//         }
        
//         nodeStack.push(root);
//         depthStack.push(1);
        
//         while (!nodeStack.isEmpty()) {
//             TreeNode curr = nodeStack.pop();
//             int depth = depthStack.pop();
//             res = Math.max(res, depth);
            
//             if (curr.right != null) {
//                 nodeStack.push(curr.right);
//                 depthStack.push(depth + 1);
//             }
            
//             if (curr.left != null) {
//                 nodeStack.push(curr.left);
//                 depthStack.push(depth + 1);
//             }
//         }
        
//         return res;
//     }
// }

// BFS Iterative
class Solution {
    public int maxDepth(TreeNode root) {
        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        
        if (root == null) {
            return res;
        }
        
        queue.add(root);
        
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            while (size > 0) {
                TreeNode curr = queue.remove();
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
                size--;
            }
        }
        
        return res;
    }
}