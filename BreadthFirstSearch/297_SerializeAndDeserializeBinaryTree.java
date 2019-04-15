/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        
        List<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode curr = queue.remove(0);

            if (curr == null) {
                sb.append("null");
            } else {
                sb.append(curr.val);
                queue.add(curr.left);
                queue.add(curr.right);
            }
            
            sb.append(",");
        }
        
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        data = data.substring(1, data.length() - 1);
        if (data.equals("")) {
            return null;
        }
        
        String[] dataArray = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(dataArray[0]));
        queue.add(root);
        
        for (int i = 1; i < dataArray.length; i++) {
            TreeNode curr = queue.remove();
            
            if (!dataArray[i].equals("null")) {
                int left = Integer.parseInt(dataArray[i]);
                curr.left = new TreeNode(left);
                queue.add(curr.left);
            }
            i++;
            if (!dataArray[i].equals("null")) {
                int right = Integer.parseInt(dataArray[i]);
                curr.right = new TreeNode(right);
                queue.add(curr.right);
            }
        }
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));