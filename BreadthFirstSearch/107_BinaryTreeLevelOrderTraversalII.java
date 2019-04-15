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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        
        if (root == null) {
            return res;
        }
        
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            
            while (size > 0) {
                TreeNode curr = queue.remove();
                list.add(curr.val);
                
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                
                if (curr.right != null) {
                    queue.add(curr.right);
                }
                
                size--;
            }
            
            res.add(0, list);
        }
        
        return res;
    }
} 