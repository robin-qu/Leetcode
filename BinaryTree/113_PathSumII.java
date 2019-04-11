/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Traversal
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> paths = new LinkedList<>();
        if (root == null) {
            return paths;
        }
        List<Integer> path = new LinkedList<>();
        path.add(root.val);
        helper(root, sum, path, paths);
        return paths;
    }
    
    public void helper(TreeNode root,
                       int sum,
                       List<Integer> path,
                       List<List<Integer>> paths) {
        if (root.left == null && root.right == null && root.val == sum) {
            paths.add(path);
        }
        
        if (root.left != null) {
            List<Integer> newPath = new LinkedList<>();
            for (int node : path) {
                newPath.add(node);
            }
            newPath.add(root.left.val);
            helper(root.left, sum - root.val, newPath, paths);
        }
        
        if (root.right != null) {
            List<Integer> newPath = new LinkedList<>();
            for (int node : path) {
                newPath.add(node);
            }
            newPath.add(root.right.val);
            helper(root.right, sum - root.val, newPath, paths);
        }
    }
}


// // Divide and Conquer
// class Solution {
//     public List<List<Integer>> pathSum(TreeNode root, int sum) {
//         List<List<Integer>> paths = new LinkedList<>();
        
//         if (root == null) {
//             return paths;
//         }
        
//         if (root.left != null && root.right != null && root.val == sum) {
//             List<Integer> path = new LinkedList<>();
//             path.add(root.val);
//             paths.add(path);
//             return paths;
//         }
        
//         List<List<Integer>> left = pathSum(root.left, sum - root.val);
//         List<List<Integer>> right = pathSum(root.right, sum - root.val);
        
//         for (List<Integer> path : left) {
//             List<Integer> newPath = new LinkedList<>();
//             newPath.add(root.val);
//             for (int node : path) {
//                 newPath.add(node);
//             }
//             paths.add(newPath);
//         }
        
//         for (List<Integer> path : right) {
//             List<Integer> newPath = new LinkedList<>();
//             newPath.add(root.val);
//             for (int node : path) {
//                 newPath.add(node);
//             }
//             paths.add(newPath);
//         }
        
//         return paths;
//     }
// }


// // Iterative
// class Solution {
//     public List<List<Integer>> pathSum(TreeNode root, int sum) {
//         Stack<TreeNode> stack = new Stack<>();
//         Stack<List<Integer>> pathStack = new Stack<>();
//         Stack<Integer> sumStack = new Stack<>();
//         List<List<Integer>> res = new LinkedList<>();
        
//         if (root == null) {
//             return res;
//         }
        
//         stack.push(root);
//         List<Integer> path = new LinkedList<>();
//         path.add(root.val);
//         pathStack.push(path);
//         sumStack.push(root.val);
        
//         while (!stack.isEmpty()) {
//             TreeNode curr = stack.pop();
//             List<Integer> currPath = pathStack.pop();
//             int currSum = sumStack.pop();
            
//             if (curr.left == null && curr.right == null && sum == currSum) {
//                 res.add(currPath);
//             }
            
//             if (curr.right != null) {
//                 stack.push(curr.right);
//                 List<Integer> newPath = new LinkedList<Integer>();
//                 for (int node : currPath) {
//                     newPath.add(node);
//                 }
//                 newPath.add(curr.right.val);
//                 pathStack.push(newPath);
//                 sumStack.push(currSum + curr.right.val);
//             }
            
//             if (curr.left != null) {
//                 stack.push(curr.left);
//                 List<Integer> newPath = new LinkedList<Integer>();
//                 for (int node : currPath) {
//                     newPath.add(node);
//                 }
//                 newPath.add(curr.left.val);
//                 pathStack.push(newPath);
//                 sumStack.push(currSum + curr.left.val);
//             }
//         }
        
//         return res;        
//     }
// }


// // Traversal
// class Solution {
//     public List<List<Integer>> pathSum(TreeNode root, int sum) {
//         List<List<Integer>> res = new LinkedList<>();
//         List<Integer> path = new LinkedList<>();
//         helper(root, sum, path, res);
//         return res;
//     }
    
//     public void helper(TreeNode root, int sum, List<Integer> path, List<List<Integer>> paths) {
//         if (root == null) {
//             return;
//         }
        
//         path.add(root.val);
        
//         if (root.left == null && root.right == null) {
//             if (root.val == sum) {
//                 paths.add(new ArrayList<Integer>(path));
//             }
//             return;
//         }
        
//         if (root.left != null) {
//             helper(root.left, sum - root.val, path, paths);
//             path.remove(path.size() - 1);
//         }
        
//         if (root.right != null) {
//             helper(root.right, sum - root.val, path, paths);
//             path.remove(path.size() - 1);
//         }
//     }
// }
