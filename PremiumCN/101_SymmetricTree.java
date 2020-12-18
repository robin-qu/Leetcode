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
//     public boolean isSymmetric(TreeNode root) {
//         if (root == null) {
//             return true;
//         }

//         return helper(root, root);
//     }

//     private boolean helper(TreeNode left, TreeNode right) {
//         if (left == null && right == null) {
//             return true;
//         }

//         if (left == null || right == null) {
//             return false;
//         }

//         if (left.val != right.val) {
//             return false;
//         }

//         return helper(left.left, right.right) && helper(left.right, right.left);
//     }
// }


// class Solution {
//     public boolean isSymmetric(TreeNode root) {
//         if (root == null) {
//             return true;
//         }

//         Stack<TreeNode> stack1 = new Stack<>();
//         Stack<TreeNode> stack2 = new Stack<>();
//         stack1.push(root);
//         stack2.push(root);
//         while (!stack1.isEmpty() && !stack2.isEmpty()) {
//             TreeNode curr1 = stack1.pop();
//             TreeNode curr2 = stack2.pop();
//             if (curr1 == null && curr2 == null) {
//                 continue;
//             }
//             if (curr1 == null || curr2 == null || curr1.val != curr2.val) {
//                 return false;
//             }
//             stack1.push(curr1.right);            
//             stack1.push(curr1.left);
//             stack2.push(curr2.left);
//             stack2.push(curr2.right);
//         }

//         return stack1.isEmpty() && stack2.isEmpty();
//     }
// }


class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr1 = queue.poll();
            TreeNode curr2 = queue.poll();
            if (curr1 == null && curr2 == null) {
                continue;
            }
            if (curr1 == null || curr2 == null || curr1.val != curr2.val) {
                return false;
            }
            queue.offer(curr1.right);
            queue.offer(curr2.left);            
            queue.offer(curr1.left);
            queue.offer(curr2.right);
        }

        return true;
    }
}