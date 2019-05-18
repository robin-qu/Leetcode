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
//     public int maxDepth(TreeNode root) {
//         if (root == null) {
//             return 0;
//         }
        
//         int left = maxDepth(root.left);
//         int right = maxDepth(root.right);
//         return 1 + Math.max(left, right);
//     }
// }

// class Solution {
//     private int max;
    
//     public int maxDepth(TreeNode root) {
//         max = 0;
//         helper(root, 0);
//         return max;
//     }
    
//     private void helper(TreeNode root, int level) {
//         if (root == null) {
//             max = Math.max(max, level);
//             return;
//         }
        
//         helper(root.left, level + 1);
//         helper(root.right, level + 1);
//     }
// }


// class Solution {
//     public int maxDepth(TreeNode root) {
//         int res = 0;
//         if (root == null) {
//             return res;
//         }
//         Stack<TreeNode> stack = new Stack<>();
//         Stack<Integer> depth = new Stack<>();
//         stack.push(root);
//         depth.push(1);
        
//         while (!stack.isEmpty()) {
//             TreeNode curr = stack.pop();
//             int currDepth = depth.pop();
            
//             if (curr.left == null && curr.right == null) {
//                 res = Math.max(res, currDepth);
//             }
            
//             if (curr.left != null) {
//                 stack.push(curr.left);
//                 depth.push(currDepth + 1);
//             }
            
//             if (curr.right != null) {
//                 stack.push(curr.right);
//                 depth.push(currDepth + 1);
//             }
//         }
        
//         return res;
//     }
// }

class Solution {
    public int maxDepth(TreeNode root) {
        int res = 0;
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            res++;
            
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }
        return res;
    }
}