// /**
//  * Definition for a binary tree node.
//  * public class TreeNode {
//  *     int val;
//  *     TreeNode left;
//  *     TreeNode right;
//  *     TreeNode(int x) { val = x; }
//  * }
//  */
// public class Codec {

//     // Encodes a tree to a single string.
//     public String serialize(TreeNode root) {
//         StringBuilder sb = new StringBuilder();
//         if (root == null) {
//             return sb.toString();
//         }
//         Queue<TreeNode> queue = new LinkedList<>();
//         queue.offer(root);
//         sb.append(root.val);
//         while (!queue.isEmpty()) {
//             TreeNode curr = queue.poll();
//             if (curr != null) {
//                 queue.offer(curr.left);
//                 sb.append(",");
//                 sb.append(curr.left == null ?  "#" : curr.left.val);
//                 queue.offer(curr.right);
//                 sb.append(",");
//                 sb.append(curr.right == null ? "#" : curr.right.val);
//             }
//         }
//         return sb.toString();
//     }

//     // Decodes your encoded data to tree.
//     public TreeNode deserialize(String data) {
//         if (data.length() == 0) {
//             return null;
//         }
//         String[] nodes = data.split(",");
//         Queue<TreeNode> queue = new LinkedList<>();
//         TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
//         queue.offer(root);
//         int idx = 0;
//         while (!queue.isEmpty()) {
//             TreeNode curr = queue.poll();
//             idx++;
//             TreeNode left = null;
//             if (!nodes[idx].equals("#")) {
//                 left = new TreeNode(Integer.parseInt(nodes[idx]));
//                 queue.offer(left);
//             }
//             idx++;
//             TreeNode right = null;
//             if (!nodes[idx].equals("#")) {
//                 right = new TreeNode(Integer.parseInt(nodes[idx]));
//                 queue.offer(right);
//             }
//             curr.left = left;
//             curr.right = right;
//         }
        
//         return root;
//     }
// }

// // Your Codec object will be instantiated and called as such:
// // Codec ser = new Codec();
// // Codec deser = new Codec();
// // TreeNode ans = deser.deserialize(ser.serialize(root));



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
            return "#";
        }
        
        StringBuilder sb = new StringBuilder();
        String left = serialize(root.left);
        String right = serialize(root.right);
        sb.append(root.val).append(",").append(left).append(",").append(right);
        
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        Deque<String> dq = new LinkedList<>();
        for (String node : nodes) {
            dq.offerLast(node);
        }
        return helper(dq);
    }
                                        
    private TreeNode helper(Deque<String> dq) {
        if (dq.peekFirst().equals("#")) {
            dq.pollFirst();
            return null;
        }
        
        TreeNode root = new TreeNode(Integer.parseInt(dq.pollFirst()));
        root.left = helper(dq);
        root.right = helper(dq);
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));