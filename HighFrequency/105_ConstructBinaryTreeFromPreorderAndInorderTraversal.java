/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// // dfs O(n^2)time O(n)space
// class Solution {
//     public TreeNode buildTree(int[] preorder, int[] inorder) {
//         if (preorder == null || inorder == null || preorder.length != inorder.length) {
//             return null;
//         }
        
//         int n = preorder.length;
        
//         return helper(preorder, 0, n - 1, inorder, 0, n - 1);
//     }
    
//     private TreeNode helper(int[] preorder, int preLeft, int preRight,
//                             int[] inorder, int inLeft, int inRight) {
//         if (preLeft > preLeft || inLeft > inRight) {
//             return null;
//         }
        
//         TreeNode root = new TreeNode(preorder[preLeft]);
//         int idx = inLeft;
//         while (inorder[idx] != preorder[preLeft]) {
//             idx++;
//         }
        
//         int leftLen = idx - inLeft;
        
//         root.left = helper(preorder, preLeft + 1, preLeft + leftLen, 
//                            inorder, inLeft, idx - 1);
//         root.right = helper(preorder, preLeft + leftLen + 1, preRight, 
//                             inorder, idx + 1, inRight);
        
//         return root;
//     }
// }


// dfs O(n)time O(n)space
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        
        int n = preorder.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        
        return helper(preorder, 0, n - 1, inorder, 0, n - 1, map);
    }
    
    private TreeNode helper(int[] preorder, int preLeft, int preRight,
                            int[] inorder, int inLeft, int inRight,
                            Map<Integer, Integer> map) {
        if (preLeft > preLeft || inLeft > inRight) {
            return null;
        }
        
        TreeNode root = new TreeNode(preorder[preLeft]);
        int idx = map.get(preorder[preLeft]);
        
        int leftLen = idx - inLeft;
        
        root.left = helper(preorder, preLeft + 1, preLeft + leftLen, 
                           inorder, inLeft, idx - 1, map);
        root.right = helper(preorder, preLeft + leftLen + 1, preRight, 
                            inorder, idx + 1, inRight, map);
        
        return root;
    }
}