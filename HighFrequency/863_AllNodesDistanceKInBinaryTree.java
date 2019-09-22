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
//     public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
//         if (root == null || target == null) {
//             return new ArrayList<>();
//         }
        
//         List<Integer> res = new ArrayList<>();
        
//         getDesc(target, K, res);
        
//         return res;
//     }
    
//     private void getDesc(TreeNode root, int k, List<Integer> res) {
//         if (root == null) {
//             return;
//         }
        
//         if (k == 0) {
//             res.add(root.val);
//             return;
//         }
        
//         getDesc(root.left, k - 1, res);
//         getDesc(root.right, k - 1, res);
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

// tree to graph O(n)time O(n)space
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if (root == null || target == null) {
            return new ArrayList<>();
        }
        
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        toGraph(root, null, graph);
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(target.val);
        Set<Integer> visited = new HashSet<>();
        visited.add(target.val);
        List<Integer> res = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (K == 0) {
                    res.add(curr);
                    continue;
                }
                
                for (int neighbor : graph.get(curr)) {
                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }                    
                }
            }
            
            K--;
        }
        
        return res;
    }
    
    private void toGraph(TreeNode root, TreeNode parent, Map<Integer, Set<Integer>> graph) {
        if (root == null) {
            return;
        }
        
        Set<Integer> neighbors = new HashSet<>();
        
        if (parent != null) {
            neighbors.add(parent.val);
        }
        if (root.left != null) {
            neighbors.add(root.left.val);
        }
        if (root.right != null) {
            neighbors.add(root.right.val);
        }
        
        graph.put(root.val, neighbors);
        
        toGraph(root.left, root, graph);
        toGraph(root.right, root, graph);
    }
}