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


public class Solution {
    /*
     * @param root: The root of the binary tree.
     * @param A: A TreeNode
     * @param B: A TreeNode
     * @return: Return the LCA of the two nodes.
     */
     
    class ResultType {
        public TreeNode node;
        public boolean findA;
        public boolean findB;
        
        public ResultType(TreeNode node, boolean findA, boolean findB) {
            this.node = node;
            this.findA = findA;
            this.findB = findB;
        }
    }
    
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        ResultType res = helper(root, A, B);
        if (res.findA && res.findB) {
            return res.node;
        }
        return null;
    }
    
    private ResultType helper(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) {
            return new ResultType(null, false, false);
        }
        
        ResultType left = helper(root.left, A, B);
        ResultType right = helper(root.right, A, B);
        
        boolean findA = (root == A) || (left.findA) || (right.findA);
        boolean findB = (root == B) || (left.findB) || (right.findB);
        
        TreeNode node;
        
        if (root == A || root == B) {
            node = root;
        } else {
            if (left.node != null && right.node != null) {
                node = root;
            } else if (left.node != null) {
                node = left.node;
            } else if (right.node != null) {
                node = right.node;
            } else {
                node = null;
            }
        }
        
        return new ResultType(node, findA, findB);
    }
}