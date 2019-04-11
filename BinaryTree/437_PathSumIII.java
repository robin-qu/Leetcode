// /**
//  * Definition for a binary tree node.
//  * public class TreeNode {
//  *     int val;
//  *     TreeNode left;
//  *     TreeNode right;
//  *     TreeNode(int x) { val = x; }
//  * }
//  */

// Divide and Conquer
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        
        return pathFromRoot(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int pathFromRoot(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        
        return (root.val == sum ? 1 : 0) + pathFromRoot(root.left, sum - root.val) + pathFromRoot(root.right, sum - root.val);
    }
}
