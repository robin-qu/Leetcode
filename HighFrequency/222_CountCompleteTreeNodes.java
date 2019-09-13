/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// // general solution O(n)time O(logn)space
// class Solution {
//     public int countNodes(TreeNode root) {
//         if (root == null) {
//             return 0;
//         }
        
//         Queue<TreeNode> queue = new LinkedList<>();
//         queue.offer(root);
//         int res = 0;
        
//         while (!queue.isEmpty()) {
//             int size = queue.size();
//             res += size;
            
//             for (int i = 0; i < size; i++) {
//                 TreeNode curr = queue.poll();
                
//                 if (curr.left != null) {
//                     queue.offer(curr.left);
//                 }
                
//                 if (curr.right != null) {
//                     queue.offer(curr.right);
//                 }
//             }
//         }
        
//         return res;
//     }
// }

// // general solution O(logn*logn)time O(logn)space
// class Solution {
//     public int countNodes(TreeNode root) {
//         if (root == null) {
//             return 0;
//         }
        
//         int leftHeight = 0;
//         int rightHeight = 0;
//         TreeNode left = root;
//         TreeNode right = root;
        
//         while (left != null) {
//             left = left.left;
//             leftHeight++;
//         }
        
//         while (right != null) {
//             right = right.right;
//             rightHeight++;
//         }
        
//         if (leftHeight == rightHeight) {
//             return (1 << leftHeight) - 1;
//         }
        
//         return 1 + countNodes(root.left) + countNodes(root.right);
//     }
// }


// general solution O(logn*logn)time O(logn)space
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int height = getHeight(root);
        int res = 0;
        
        while (root != null) {
            if (getHeight(root.right) == height - 1) {  // right is complete
                res += 1 << height;  // number of left node
                root = root.right;
            } else {   // right is not complete
                res += 1 << (height - 1);  // number of right node
                root = root.left;
            }
            
            height--;
        }
        
        return res;
    }
    
    private int getHeight(TreeNode root) {
        int height = 0;
        while (root != null) {
            height++;
            root = root.left;
        }
        
        return height - 1;
    }
}