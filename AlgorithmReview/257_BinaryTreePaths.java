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
//     public List<String> binaryTreePaths(TreeNode root) {
//         List<String> res = new ArrayList<>();
//         if (root == null) {
//             return res;
//         }
        
//         helper(root, "" + root.val, res);
//         return res;
//     }
    
//     private void helper(TreeNode root, String path, List<String> paths) {
//         if (root.left == null && root.right == null) {
//             paths.add(path);
//         }
        
//         if (root.left != null) {
//             helper(root.left, path + "->" + root.left.val, paths);
//         }
        
//         if (root.right != null) {
//             helper(root.right, path + "->" + root.right.val, paths);
//         }
//     }
// }

// class Solution {
//     public List<String> binaryTreePaths(TreeNode root) {
//         List<String> currPath = new ArrayList<>();
//         if (root == null) {
//             return currPath;
//         }
        
//         if (root.left == null && root.right == null) {
//             currPath.add("" + root.val);
//             return currPath;
//         }
        
//         List<String> left = binaryTreePaths(root.left);
//         List<String> right = binaryTreePaths(root.right);
        
//         for (String path : left) {
//             currPath.add(root.val + "->" + path);
//         }
//         for (String path : right) {
//             currPath.add(root.val + "->" + path);
//         }
        
//         return currPath;
//     }
// }


class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        Stack<String> paths = new Stack<>();
        stack.push(root);
        paths.push("" + root.val);
        
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            String path = paths.pop();
            
            if (curr.left == null && curr.right == null) {
                res.add(path);
            }
            
            if (curr.right != null) {
                stack.push(curr.right);
                paths.push(path + "->" + curr.right.val);
            }
            
            if (curr.left != null) {
                stack.push(curr.left);
                paths.push(path + "->" + curr.left.val);
            }
        }
        
        return res;
    }
} 