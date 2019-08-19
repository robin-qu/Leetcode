/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/

// BFS O(n)time O(n)space
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        Map<Node, Node> map = new HashMap<>();
        map.put(node, new Node(node.val, new ArrayList<>()));
        
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            
            for (Node neighbor : curr.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    queue.offer(neighbor);
                }
            }
        }
        
        for (Node vertex : map.keySet()) {
            for (Node neighbor : vertex.neighbors) {
                map.get(vertex).neighbors.add(map.get(neighbor));
            }
        }
        
        return map.get(node);
    }
}