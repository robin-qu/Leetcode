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
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            
            if (curr == null) {
                sb.append(",null");
            } else {
                sb.append(",");
                sb.append(curr.val);
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }
        
        sb.append(']');
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        data = data.substring(2, data.length() - 1);
        String[] datas = data.split(",");
        if (datas[0].equals("null")) {
            return null;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(datas[0]));
        queue.offer(root);
        int i = 1;
        
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            
            if (!datas[i].equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(datas[i]));
                queue.offer(curr.left);
            }
            i++;
            if (!datas[i].equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(datas[i]));
                queue.offer(curr.right);
            }
            i++;
        }
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));