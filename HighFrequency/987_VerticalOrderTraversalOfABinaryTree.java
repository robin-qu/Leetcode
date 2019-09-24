/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// class Solution {
//     public List<List<Integer>> verticalTraversal(TreeNode root) {
//         if (root == null) {
//             return new ArrayList<>();
//         }
//         Map<Integer, List<Integer>> map = new HashMap<>();
        
//         helper(root, 0, map);
//         List<List<Integer>> res = new ArrayList<>();
        
//         int min = 0;
//         int max = 0;
//         for (int key : map.keySet()) {
//             min = Math.min(min, key);
//             max = Math.max(max, key);
//         }
        
//         for (int i = min; i <= max; i++) {
//             if (map.containsKey(i)) {
//                 res.add(map.get(i));
//             }
//         }
        
//         return res;
//     }
    
//     private void helper(TreeNode root, int pos, Map<Integer, List<Integer>> map) {
//         if (root == null) {
//             return;
//         }
        
//         if (!map.containsKey(pos)) {
//             map.put(pos, new ArrayList<>());
//         }
        
//         map.get(pos).add(root.val);
        
//         helper(root.left, pos - 1, map);
//         helper(root.right, pos + 1, map);
//     }
// }


class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        
        Map<Integer, List<int[]>> map = new HashMap<>();
        int min = 0;
        int max = 0;
        Queue<TreeNode> nQueue = new LinkedList<>();
        nQueue.offer(root);
        Queue<Integer> pQueue = new LinkedList<>();
        pQueue.offer(0);
        int level = 0;
        
        while (!nQueue.isEmpty()) {
            int size = nQueue.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode curr = nQueue.poll();
                int pos = pQueue.poll();

                if (!map.containsKey(pos)) {
                    map.put(pos, new ArrayList<>());
                }
                map.get(pos).add(new int[]{curr.val, level, pos});

                if (curr.left != null) {
                    nQueue.offer(curr.left);
                    pQueue.offer(pos - 1);
                    min = Math.min(pos - 1, min);
                }

                if (curr.right != null) {
                    nQueue.offer(curr.right);
                    pQueue.offer(pos + 1);
                    max = Math.max(pos + 1, max);
                }
            }
            
            level++;
        }
        
        List<List<Integer>> res = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (map.containsKey(i)) {
                Collections.sort(map.get(i), new Comparator<int[]>() {
                    @Override
                    public int compare(int[] a, int[] b) {
                        if (a[1] != b[1]) {
                            return a[1] - b[1];
                        }
                        
                        return a[0] - b[0];
                    }
                });
                List<Integer> list = new ArrayList<>();
                for (int[] pair : map.get(i)) {
                    list.add(pair[0]);
                }
                res.add(list);
            }
        }
        
        return res;
    }
}