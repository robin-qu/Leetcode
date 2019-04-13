/**
 * Given a binary tree, find the subtree with minimum sum. Return the root of the subtree.
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

// // Traversal
// public class Solution {
//     /**
//      * @param root: the root of binary tree
//      * @return: the root of the minimum subtree
//      */
     
//     private TreeNode res = null;
//     private int min = Integer.MAX_VALUE;
    
//     public TreeNode findSubtree(TreeNode root) {
//         helper(root);
//         return res;
//     }
    
//     private int helper(TreeNode root) {
//         if (root == null) {
//             return 0;
//         }
        
//         int sum = root.val + helper(root.left) + helper(root.right);
        
//         if (sum < min) {
//             min = sum;
//             res = root;
//         }
        
//         return sum;
//     }
// }


// Divide and Conquer
class ResultType {
    public TreeNode root;
    public int sum;
    public int min;
    
    public ResultType(TreeNode root, int sum, int min) {
        this.root = root;
        this.sum = sum;
        this.min = min;
    }
}

public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the root of the minimum subtree
     */
    public TreeNode findSubtree(TreeNode root) {
        return helper(root).root;
    }
    
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(null, 0, Integer.MAX_VALUE);
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        ResultType result = new ResultType(root,
                                root.val + left.sum + right.sum,
                                root.val + left.sum + right.sum);
                                
        if (left.min < result.min) {
            result.min = left.min;
            result.root = left.root;
        }
        
        if (right.min < result.min) {
            result.min = right.min;
            result.root = right.root;
        }
        
        return result;
    }
}

// // DFS Iterative
// public class Solution {
//     /**
//      * @param root: the root of binary tree
//      * @return: the root of the minimum subtree
//      */
//     public TreeNode findSubtree(TreeNode root) {
//         Stack<TreeNode> stack = new Stack<>();
//         int min = Integer.MAX_VALUE;
//         TreeNode res = null;
        
//         if (root == null) {
//             return res;
//         }
        
//         stack.push(root);
        
//         while (!stack.isEmpty()) {
//             TreeNode curr = stack.pop();
//             int sum = getSum(curr);
            
//             if (sum < min) {
//                 res = curr;
//             }
            
//             if (curr.left != null) {
//                 stack.push(curr.left);
//             }
            
//             if (curr.right != null) {
//                 stack.push(curr.right);
//             }
//         }
        
//         return res;
//     }
    
//     private int getSum(TreeNode root) {
//         if (root == null) {
//             return 0;
//         }
        
//         return root.val + getSum(root.left) + getSum(root.right);
//     }
// }


// // BFS Iterative
// public class Solution {
//     /**
//      * @param root: the root of binary tree
//      * @return: the root of the minimum subtree
//      */
//     public TreeNode findSubtree(TreeNode root) {
//         Queue<TreeNode> queue = new LinkedList<>();
//         int min = Integer.MAX_VALUE;
//         TreeNode res = null;
        
//         if (root == null) {
//             return res;
//         }
        
//         queue.add(root);
        
//         while (!queue.isEmpty()) {
//             TreeNode curr = queue.remove();
//             int sum = getSum(curr);
            
//             if (sum < min) {
//                 res = curr;
//             }
            
//             if (curr.left != null) {
//                 queue.add(curr.left);
//             }
            
//             if (curr.right != null) {
//                 queue.add(curr.right);
//             }
//         }
        
//         return res;
//     }
    
//     private int getSum(TreeNode root) {
//         if (root == null) {
//             return 0;
//         }
        
//         return root.val + getSum(root.left) + getSum(root.right);
//     }
// }