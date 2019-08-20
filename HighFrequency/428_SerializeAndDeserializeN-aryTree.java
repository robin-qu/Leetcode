/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

// BFS O(n)time O(n)space
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            sb.append(curr.val);
            sb.append(',');
            sb.append(curr.children.size());  // add the number of children after the node value
            sb.append(',');
            
            for (Node child : curr.children) {
                queue.offer(child);
            }
        }
        
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.equals("")) {
            return null;
        }
        
        String[] datas = data.split(",");
        int idx = 0;
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(Integer.parseInt(datas[idx]), new ArrayList<>());
        queue.offer(root);
        idx++;
        Queue<Integer> countQueue = new LinkedList<>();
        countQueue.offer(Integer.parseInt(datas[idx]));
        idx++;
        
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            int childrenNum = countQueue.poll();
            
            for (int i = 0; i < childrenNum; i++) {
                Node child = new Node(Integer.parseInt(datas[idx]), new ArrayList<>());
                curr.children.add(child);
                queue.offer(child);
                idx++;
                countQueue.offer(Integer.parseInt(datas[idx]));
                idx++;
            }
        }
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));