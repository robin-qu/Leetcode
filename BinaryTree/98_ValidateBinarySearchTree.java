/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */



// // Divide and Conquer
// class Solution {
//     public boolean isValidBST(TreeNode root) {
//         return helper(root, -Long.MAX_VALUE, Long.MAX_VALUE);
//     }
    
//     public boolean helper(TreeNode root, long min, long max) {
//         if (root == null) {
//             return true;
//         }
        
//         if (root.val <= min || root.val >= max) {
//             return false;
//         }
        
//         return helper(root.left, min, root.val) && helper(root.right, root.val, max);
//     }
// }

// // Divide and Conquer
// class Solution {
//     class ResultType {
//         public boolean isValid;
//         public int max;
//         public int min;
        
//         public ResultType(boolean isValid, int max, int min) {
//             this.isValid = isValid;
//             this.max = max;
//             this.min = min;
//         }
//     }
    
//     public boolean isValidBST(TreeNode root) {
//         return helper(root).isValid;
//     }
    
//     public ResultType helper(TreeNode root) {
//         if (root == null) {
//             return new ResultType(true, -Integer.MAX_VALUE, Integer.MIN_VALUE);
//         }
        
//         ResultType left = helper(root.left);
//         ResultType right = helper(root.right);
        
//         if (root.left == null && root.right == null) {
//             return new ResultType(true, root.val, root.val);
//         }
        
//         if (root.left != null && left.max >= root.val) {
//             return new ResultType(false, 0, 0);
//         }
        
//         if (root.right != null && right.min <= root.val) {
//             return new ResultType(false, 0, 0);
//         }
        
//         boolean isValid = left.isValid && right.isValid;
//         int max = root.right == null ? root.val : right.max;
//         int min = root.left == null ? root.val : left.min;
        
//         return new ResultType(isValid, max, min);
//     }
// }


// // Iterative
// class Solution {
//     public boolean isValidBST(TreeNode root) {
//         Stack<TreeNode> stack = new Stack<>();
//         Stack<Long> mins = new Stack<>();
//         Stack<Long> maxs =  new Stack<>();
        
//         if (root == null) {
//             return true;
//         }
        
//         stack.push(root);
//         mins.push(-Long.MAX_VALUE);
//         maxs.push(Long.MAX_VALUE);
        
//         while (!stack.isEmpty()) {
//             TreeNode curr = stack.pop();
//             long min = mins.pop();
//             long max = maxs.pop();
            
//             if (curr.val <= min || curr.val >= max) {
//                 return false;
//             }
            
//             if (curr.left != null) {
//                 stack.push(curr.left);
//                 mins.push(min);
//                 maxs.push((long) curr.val);
//             }
            
//             if (curr.right != null) {
//                 stack.push(curr.right);
//                 mins.push((long) curr.val);
//                 maxs.push(max);
//             }
//         }
        
//         return true;
//     }
// } 

// Iterative2 (assume no dulpicate)
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode prev = null;
        
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            
            curr = stack.pop();
            if (prev != null && prev.val >= curr.val) {
                return false;
            }
            prev = curr;
            curr = curr.right;
        }
        
        return true;
    }
} 