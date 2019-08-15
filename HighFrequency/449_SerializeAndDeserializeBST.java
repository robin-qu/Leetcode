/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Preorder traversal O(n^2)time O(n^2)space
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            
            sb.append(curr.val);
            sb.append(' ');
            
            if (curr.right != null) {
                stack.push(curr.right);
            }
            
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        }
        
        String[] datas = data.split(" ");
        
        TreeNode curr = new TreeNode(Integer.parseInt(datas[0]));
        
        StringBuilder left = new StringBuilder();  // nodes smaller than root
        StringBuilder right = new StringBuilder();  // nodes bigger than root
        
        for (int i = 1; i < datas.length; i++) {
            if (Integer.parseInt(datas[i]) < curr.val) {
                left.append(datas[i]);
                left.append(' ');
            } else {
                right.append(datas[i]);
                right.append(' ');
            }
        }
        
        curr.left = deserialize(left.toString());
        curr.right = deserialize(right.toString());
        
        return curr;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));