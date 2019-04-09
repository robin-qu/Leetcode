/**
 * Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
 * The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
 * Return null if LCA does not exist.
 * 
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


public class Solution {
    /*
     * @param root: The root of the binary tree.
     * @param A: A TreeNode
     * @param B: A TreeNode
     * @return: Return the LCA of the two nodes.
     */
     class ResultType {
         public TreeNode node;
         public boolean existA;
         public boolean existB;
         
         public ResultType(TreeNode node, boolean existA, boolean existB) {
             this.node = node;
             this.existA = existA;
             this.existB = existB;
         }
     }
     
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        ResultType res = helper(root, A, B);
        if (res.existA && res.existB) {
            return res.node;
        }
        return null;
    }
    
    private ResultType helper(TreeNode root, TreeNode A, TreeNode B) {
        ResultType curr = new ResultType(null, false, false);
        
        if (root == null) {
            return curr;
        }
        
        ResultType left = helper(root.left, A, B);
        ResultType right = helper(root.right, A, B);
        
        curr.existA = (root == A) || left.existA || right.existA;
        curr.existB = (root == B) || left.existB || right.existB;
        
        if (root == A || root == B) {
            curr.node = root;
            return curr;
        }
        
        if (left.node != null && right.node != null) {
            curr.node = root;
            return curr;
        }
        
        if (left.node != null) {
            curr.node = left.node;
            return curr;
        }
        
        if (right.node != null) {
            curr.node = right.node;
            return curr;
        }
        
        return curr;
    }
}