/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// // Traversal
// class Solution {
//     public List<String> binaryTreePaths(TreeNode root) {
//         List<String> paths = new LinkedList<>();
//         if (root == null) {
//             return paths;
//         }
//         helper(root, "" + root.val, paths);
//         return paths;
//     }
    
//     private void helper(TreeNode root, String value, List<String> list) {
//         if (root.left == null && root.right == null) {
//             list.add(value);
//             return;
//         } 
        
//         if (root.left != null) {
//             helper(root.left, value + "->" + root.left.val, list);
//         }
        
//         if (root.right != null) {
//             helper(root.right, value + "->" + root.right.val, list);
//         }
//     }
// }


// // Divide and Conquer
// class Solution {
//     public List<String> binaryTreePaths(TreeNode root) {
//         List<String> paths = new LinkedList<>();
        
//         if (root == null) {
//             return paths;
//         }
        
//         if (root.left == null && root.right == null) {
//             paths.add("" + root.val);
//             return paths;
//         }
        
//         List<String> leftPaths = binaryTreePaths(root.left);
//         List<String> rightPaths = binaryTreePaths(root.right);
        
//         for (String path : leftPaths) {
//             paths.add(root.val + "->" + path);
//         }
//         for (String path : rightPaths) {
//             paths.add(root.val + "->" + path);
//         }
        
//         return paths;
//     }
// }

// // DFS Iterative
// class Solution {
//     public List<String> binaryTreePaths(TreeNode root) {
//         List<String> res = new LinkedList<>();
//         Stack<TreeNode> stack = new Stack<>();
//         Stack<String> paths = new Stack<>();
        
//         if (root == null) {
//             return res;
//         }
        
//         stack.push(root);
//         paths.push("" + root.val);
        
//         while (!stack.isEmpty()) {
//             TreeNode curr = stack.pop();
//             String currPath = paths.pop();
            
//             if (curr.left == null && curr.right == null) {
//                 res.add(currPath);
//             }
            
//             if (curr.left != null) {
//                 stack.push(curr.left);
//                 paths.push(currPath + "->" + curr.left.val);
//             }
            
//             if (curr.right != null) {
//                 stack.push(curr.right);
//                 paths.push(currPath + "->" + curr.right.val);
//             }
//         }
//         return res;
//     }
// }

// BFS Iterative
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<String> path = new LinkedList<>();
        
        if (root == null) {
            return res;
        }
        
        queue.add(root);
        path.add("" + root.val);
        
        while (!queue.isEmpty()) {
            TreeNode curr =  queue.remove();
            String currPath = path.remove();
            
            if (curr.left == null && curr.right == null) {
                res.add(currPath);
            }
            
            if (curr.left != null) {
                queue.add(curr.left);
                path.add(currPath + "->" + curr.left.val);
            }
            
            if (curr.right != null) {
                queue.add(curr.right);
                path.add(currPath + "->" + curr.right.val);
            }
        }
        
        return res;
    }
}