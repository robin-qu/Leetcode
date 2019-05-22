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

// public class Solution {
//     /**
//      * @param root: param root: The root of the binary search tree
//      * @param k1: An integer
//      * @param k2: An integer
//      * @return: return: Return all keys that k1<=key<=k2 in ascending order
//      */
//     public List<Integer> searchRange(TreeNode root, int k1, int k2) {
//         List<Integer> res = new ArrayList<>();
//         Stack<TreeNode> stack = new Stack<>();
//         TreeNode curr = root;
        
//         while (curr != null || !stack.isEmpty()) {
//             while (curr != null) {
//                 stack.push(curr);
//                 curr = curr.left;
//             }
            
//             curr = stack.pop();
//             if (k1 <= curr.val && curr.val <= k2) {
//                 res.add(curr.val);
//             }
            
//             if (curr.val > k2) {
//                 break;
//             }
            
//             curr = curr.right;
//         }
        
//         return res;
//     }
// }


// public class Solution {
//     /**
//      * @param root: param root: The root of the binary search tree
//      * @param k1: An integer
//      * @param k2: An integer
//      * @return: return: Return all keys that k1<=key<=k2 in ascending order
//      */
//     public List<Integer> searchRange(TreeNode root, int k1, int k2) {
//         List<Integer> res = new ArrayList<>();
//         if (root == null) {
//             return res;
//         }
        
//         List<Integer> left = searchRange(root.left, k1, k2);
//         List<Integer> right = searchRange(root.right, k1, k2);
        
//         res.addAll(left);
        
//         if (k1 <= root.val && root.val <= k2) {
//             res.add(root.val);
//         }
        
//         res.addAll(right);
        
//         return res;
//     }
// }

public class Solution {
    /**
     * @param root: param root: The root of the binary search tree
     * @param k1: An integer
     * @param k2: An integer
     * @return: return: Return all keys that k1<=key<=k2 in ascending order
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        List<Integer> res = new ArrayList<>();
        
        helper(root, k1, k2, res);
        return res;
    }
    
    private void helper(TreeNode root, int k1, int k2, List<Integer> res) {
        if (root == null) {
            return;
        }
        
        helper(root.left, k1, k2, res);
        
        if (k1 <= root.val && root.val <= k2) {
            res.add(root.val);
        }
        
        helper(root.right, k1, k2, res);
    }
}