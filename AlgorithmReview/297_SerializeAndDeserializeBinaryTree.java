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
        
        String res = "[";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                res += "null,";
                continue;
            }
            
            res += curr.val + ",";
            queue.offer(curr.left);
            queue.offer(curr.right);
        }
        
        res = res.substring(0, res.length() - 1);
        res += "]";
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("[]")) {
            return null;
        }
        
        data = data.substring(1, data.length() - 1);
        String[] values = data.split(",");
        
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int idx = 1;
        
        while (idx < values.length) {
            TreeNode curr = queue.poll();
            
            if (!values[idx].equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(values[idx]));
                queue.offer(curr.left);
            }
            idx++;
            
            if (idx < values.length && !values[idx].equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(values[idx]));
                queue.offer(curr.right);
            }
            idx++;
        }
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));