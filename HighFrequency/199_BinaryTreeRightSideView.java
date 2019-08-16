/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// // deque O(n)time O(n)space
// class Solution {
//     public List<Integer> rightSideView(TreeNode root) {
//         if (root == null) {
//             return new ArrayList<>();
//         }
        
//         Deque<TreeNode> deque = new LinkedList<>();
//         deque.offerLast(root);
//         List<Integer> res = new ArrayList<>();
        
//         while (!deque.isEmpty()) {
//             int size = deque.size();
//             res.add(deque.peekLast().val);
            
//             for (int i = 0; i < size; i++) {
//                 TreeNode curr = deque.pollFirst();
                
//                 if (curr.left != null) {
//                     deque.offerLast(curr.left);
//                 }
                
//                 if (curr.right != null) {
//                     deque.offerLast(curr.right);
//                 }
//             }
//         }
            
//         return res;
//     }
// }


// // queue O(n)time O(n)space
// class Solution {
//     public List<Integer> rightSideView(TreeNode root) {
//         if (root == null) {
//             return new ArrayList<>();
//         }
        
//         Queue<TreeNode> queue = new LinkedList<>();
//         queue.offer(root);
//         List<Integer> res = new ArrayList<>();
        
//         while (!queue.isEmpty()) {
//             int size = queue.size();
            
//             for (int i = 0; i < size; i++) {
//                 TreeNode curr = queue.poll();
                
//                 if (curr.left != null) {
//                     queue.offer(curr.left);
//                 }
                
//                 if (curr.right != null) {
//                     queue.offer(curr.right);
//                 }
                
//                 if (i == size - 1) {
//                     res.add(curr.val);
//                 }
//             }
//         }
            
//         return res;
//     }
// }


// dfs O(n)time O(n)space
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res, 0);
            
        return res;
    }
    
    private void dfs(TreeNode root, List<Integer> res, int depth) {
        if (root == null) {
            return;
        }
        
        if (depth == res.size()) {
            res.add(root.val);
        }
        
        dfs(root.right, res, depth + 1);
        dfs(root.left, res, depth + 1);
    }
}