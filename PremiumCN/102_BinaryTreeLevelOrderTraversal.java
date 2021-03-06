// /**
//  * Definition for a binary tree node.
//  * public class TreeNode {
//  *     int val;
//  *     TreeNode left;
//  *     TreeNode right;
//  *     TreeNode(int x) { val = x; }
//  * }
//  */
// class Solution {
//     public List<List<Integer>> levelOrder(TreeNode root) {
//         List<List<Integer>> res = new ArrayList<>();
//         Queue<TreeNode> queue = new LinkedList<>();
//         if (root != null) {
//             queue.offer(root);
//         }
//         while (!queue.isEmpty()) {
//             int size = queue.size();
//             List<Integer> list = new ArrayList<>();
//             for (int i = 0; i < size; i++) {
//                 TreeNode curr = queue.poll();
//                 list.add(curr.val);
//                 if (curr.left != null) {
//                     queue.offer(curr.left);
//                 }
//                 if (curr.right != null) {
//                     queue.offer(curr.right);
//                 }
//             }
//             res.add(list);
//         }

//         return res;
//     }
// }


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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 1, res);
        return res;
    }

    private void dfs(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) {
            return;
        }

        if (res.size() < level) {
            res.add(new ArrayList<>());
        }

        res.get(level - 1).add(root.val);

        dfs(root.left, level + 1, res);
        dfs(root.right, level + 1, res);
    }
}