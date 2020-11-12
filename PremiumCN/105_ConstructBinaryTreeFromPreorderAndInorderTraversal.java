/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        
        int n = preorder.length;
        
        return helper(preorder, 0, n - 1, inorder, 0, n - 1);
    }
                                        
    private TreeNode helper(int[] pre, int preS, int preE, int[] in, int inS, int inE) {
        if (preS > preE) {
            return null;
        }
    
        if (preS == preE) {
            return new TreeNode(pre[preS]);
        }
        
        int rootVal = pre[preS];
        TreeNode root = new TreeNode(rootVal);
        int rootInIdx = 0;
        for (int i = inS; i <= inE; i++) {
            if (in[i] == rootVal) {
                rootInIdx = i;
                break;
            }
        }
        
        int leftNum = rootInIdx - inS;
        
        TreeNode left = helper(pre, preS + 1, preS + leftNum, in, inS, inS + leftNum - 1);
        TreeNode right = helper(pre, preS + 1 + leftNum, preE, in, rootInIdx + 1, inE);
        
        root.left = left;
        root.right = right;
        return root;
    } 
}