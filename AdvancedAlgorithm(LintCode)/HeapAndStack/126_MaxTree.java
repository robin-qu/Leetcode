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
    /**
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] A) {
        if (A == null || A.length == 0) {
            return null;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        
        for (int i = 0; i <= A.length; i++) {
            TreeNode parent = i == A.length ? new TreeNode(Integer.MAX_VALUE) : new TreeNode(A[i]);
            while (!stack.isEmpty() && parent.val > stack.peek().val) {
                TreeNode curr = stack.pop();
                
                if (stack.isEmpty() || stack.peek().val > parent.val) {
                    parent.left = curr;
                } else {
                    stack.peek().right = curr;
                }
            }
            
            stack.push(parent);
        }
        
        return stack.peek().left;
    }
}