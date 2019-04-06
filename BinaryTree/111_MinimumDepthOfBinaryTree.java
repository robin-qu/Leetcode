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
//     public int minDepth(TreeNode root) {
//         if (root == null) {
//             return 0;
//         }
//         return helper(root, 1);
//     }
    
//     private int helper(TreeNode root, int d) {
//         if (root.left == null && root.right == null) {
//             return d;
//         }
        
//         if (root.left == null) {
//             return helper(root.right, d + 1);
//         }
        
//         if (root.right == null) {
//             return helper(root.left, d + 1);
//         }
        
//         return Math.min(helper(root.left, d + 1), helper(root.right, d + 1));
//     }
// }

// // Traversal
// class Solution {
//     public int minDepth(TreeNode root) {
//         if (root == null) {
//             return 0;
//         }
        
//         if (root.left == null && root.right == null) {
//             return 1;
//         }
        
//         if (root.left == null) {
//             return 1 + minDepth(root.right);
//         }
        
//         if (root.right == null) {
//             return 1 + minDepth(root.left);
//         }
        
//         return 1 + Math.min(minDepth(root.left), minDepth(root.right));
//     }
// }

// // DFS iterative
// class Solution {
//     public int minDepth(TreeNode root) {
//         Stack<TreeNode> nodeStack = new Stack<>();
//         Stack<Integer> depthStack = new Stack<>();
//         int res = Integer.MAX_VALUE;
        
//         if (root == null) {
//             return 0;
//         }
        
//         nodeStack.push(root);
//         depthStack.push(1);
        
//         while (!nodeStack.isEmpty()) {
//             TreeNode curr = nodeStack.pop();
//             int depth = depthStack.pop();
            
//             if (curr.left == null && curr.right == null) {
//                 res = Math.min(res, depth);
//             }
            
//             if (curr.left != null) {
//                 nodeStack.push(curr.left);
//                 depthStack.push(depth + 1);
//             }
            
//             if (curr.right != null) {
//                 nodeStack.push(curr.right);
//                 depthStack.push(depth + 1);
//             }
//         }
        
//         return res;
//     }
// }

// BFS Iterative
class Solution {
    public int minDepth(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        
        if (root == null) {
            return 0;
        }
        
        queue.add(root);
        int depth = 0;
        
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            
            while (size > 0) {
                TreeNode curr = queue.remove();
                
                if (curr.left == null && curr.right == null) {
                    return depth;
                }
                
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                
                if (curr.right != null) {
                    queue.add(curr.right);
                }
                
                size--;
            }
        }
        
        return depth;
    }
}