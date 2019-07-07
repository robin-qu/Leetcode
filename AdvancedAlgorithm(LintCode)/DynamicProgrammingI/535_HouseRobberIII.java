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

// // Initial version memorization search
// public class Solution {
//     /**
//      * @param root: The root of binary tree.
//      * @return: The maximum amount of money you can rob tonight
//      */
    
//     private Map<TreeNode, Integer> includeMap;
//     private Map<TreeNode, Integer> excludeMap;
    
//     public int houseRobber3(TreeNode root) {
//         if (root == null) {
//             return 0;
//         }
        
//         // memorization
//         this.includeMap = new HashMap<>();
//         this.excludeMap = new HashMap<>();
        
//         return Math.max(rob(root, true), rob(root, false));
//     }
    
//     // return the max amount of money robbed at node with node robbed or not robbed
//     private int rob(TreeNode node, boolean choose) {
//         if (node == null) {
//             return 0;
//         }
        
//         if (choose) {
//             if (excludeMap.containsKey(node)) {
//                 return excludeMap.get(node);
//             }
            
//             int value = node.val + rob(node.left, false) + rob(node.right, false);
//             excludeMap.put(node, value);
            
//             return value;
//         } else {
//             if (includeMap.containsKey(node)) {
//                 return includeMap.get(node);
//             }
            
//             int case1 = rob(node.left, true) + rob(node.right, true);
//             int case2 = rob(node.left, true) + rob(node.right, false);
//             int case3 = rob(node.left, false) + rob(node.right, true);
//             int case4 = rob(node.left, false) + rob(node.right, false);
            
//             int value = Math.max(Math.max(case1, case2), Math.max(case3, case4));
//             includeMap.put(node, value);
            
//             return value;
//         }
//     }
// }


// Initial version memorization search
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: The maximum amount of money you can rob tonight
     */
    public int houseRobber3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int[] res = dp(root);
        
        return Math.max(res[0], res[1]);
    }
    
    // return the max amount of money robbed at node with node robbed (first number) or not robbed (second number)
    private int[] dp(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        
        int[] left = dp(node.left);
        int[] right = dp(node.right);
        
        int include = node.val + left[1] + right[1];
        // int exclude = Math.max(Math.max(left[0] + right[0], left[0] + right[1]), Math.max(left[1] + right[0], right[1] + left[1]));
        int exclude = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        
        return new int[]{include, exclude};
    }
}