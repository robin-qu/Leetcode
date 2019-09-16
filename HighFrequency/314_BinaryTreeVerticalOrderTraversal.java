/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// // dfs O(n)time O(n)space
// class Solution {
//     public List<List<Integer>> verticalOrder(TreeNode root) {
//         if (root == null) {
//             return new ArrayList<>();
//         }
        
//         Map<Integer, List<int[]>> map = new HashMap<>();
        
//         helper(root, 0, 0, map);
        
//         int[] keys = new int[map.size()];
//         int idx = 0;
//         for (int key : map.keySet()) {
//             keys[idx++] = key;
//         }
//         Arrays.sort(keys);
        
//         List<List<Integer>> res = new ArrayList<>();
//         for (int key : keys) {
//             List<Integer> list = new ArrayList<>();
//             Collections.sort(map.get(key), new Comparator<int[]>() {
//                 @Override
//                 public int compare(int[] a, int[] b) {
//                     return a[0] - b[0];
//                 }
//             });
            
//             for (int[] vals : map.get(key)) {
//                 list.add(vals[1]);
//             }
            
//             res.add(list);
//         }
        
//         return res;
//     }
    
//     private void helper(TreeNode root, int level, int pos, Map<Integer, List<int[]>> map) {
//         if (root == null) {
//             return;
//         }
        
//         if (!map.containsKey(pos)) {
//             map.put(pos, new ArrayList<>());
//         }
        
//         map.get(pos).add(new int[]{level, root.val});
        
//         helper(root.left, level + 1, pos - 1, map);
//         helper(root.right, level + 1, pos + 1, map);
//     }
// }


// bfs O(n)time O(n)space
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        Queue<TreeNode> nQueue = new LinkedList<>();
        nQueue.offer(root);
        Queue<Integer> pQueue = new LinkedList<>();
        pQueue.offer(0);
        int min = 0;
        int max = 0;
        
        while (!nQueue.isEmpty()) {
            TreeNode curr = nQueue.poll();
            int pos = pQueue.poll();
            
            if (!map.containsKey(pos)) {
                map.put(pos, new ArrayList<>());
            }
            
            map.get(pos).add(curr.val);
            
            if (curr.left != null) {
                nQueue.offer(curr.left);
                pQueue.offer(pos - 1);
                min = Math.min(min, pos - 1);
            }
            
            if (curr.right != null) {
                nQueue.offer(curr.right);
                pQueue.offer(pos + 1);
                max = Math.max(max, pos + 1);
            }
        }
        
        List<List<Integer>> res = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (map.containsKey(i)) {
                res.add(map.get(i));
            }
        }
        return res;
    }
}