/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// // O(n)time O(n)space  with global variable
// class Solution {
//     private int max = 0;
    
//     public int diameterOfBinaryTree(TreeNode root) {
//         helper(root);
        
//         return max;
//     }
    
//     private int helper(TreeNode root) {
//         if (root == null) {
//             return 0;
//         }
        
//         int left = helper(root.left);
//         int right = helper(root.right);
        
//         max = Math.max(max, left + right);
        
//         return 1 + Math.max(left, right);
//     }
// }


// O(n)time O(n)space  without global variable
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        return helper(root)[1];  // [max height of children, max diameter]
    }
    
    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        
        int maxFromRoot = 1 + Math.max(left[0], right[0]);
        int maxDiameter = Math.max(Math.max(left[1], right[1]), left[0] + right[0]);
        
        return new int[]{maxFromRoot, maxDiameter};
    }
}