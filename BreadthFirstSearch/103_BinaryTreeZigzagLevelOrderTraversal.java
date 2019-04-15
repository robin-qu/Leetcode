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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        
        if (root == null) {
            return res;
        }
        
        queue.add(root);
        int level = 0;
        
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            
            while (size > 0) {
                TreeNode curr = queue.remove();
                if (level % 2 == 0) {
                    list.add(0, curr.val);
                } else {
                    list.add(curr.val);
                }
                
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
                size--;
            }
            res.add(list);
        }
        return res;
    }
} 