/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// BFS O(n)time O(n)space
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> res = new ArrayList<>();
        boolean order = true;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (order) {
                    level.add(curr.val);
                } else {
                    level.add(0, curr.val);
                }
                
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            
            res.add(level);
            order = !order;
        }
        
        return res;
    }
}