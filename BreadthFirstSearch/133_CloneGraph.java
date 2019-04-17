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
// class Solution {
//     public Node cloneGraph(Node node) {
//         if (node == null) {
//             return null;
//         }
        
//         Set<Node> nodes = getNodes(node);
        
//         Map<Node, Node> mapping = new HashMap<>();
//         for (Node vertex : nodes) {
//             Node newNode = new Node();
//             newNode.val = vertex.val;
//             mapping.put(vertex, newNode);
//         }
        
//         for (Node vertex : nodes) {
//             List<Node> newNeighbors = new ArrayList<>();
            
//             for (Node neighbor : vertex.neighbors) {
//                 newNeighbors.add(mapping.get(neighbor));
//             }
            
//             mapping.get(vertex).neighbors = newNeighbors;
//         }
        
//         return mapping.get(node);
//     }
    
//     // Get all the nodes in the graph
//     private Set<Node> getNodes(Node node) {
//         Set<Node> res = new HashSet<>();
//         Queue<Node> queue = new LinkedList<>();
        
//         queue.add(node);
        
//         while (!queue.isEmpty()) {
//             Node curr = queue.remove();
            
//             for (Node neighbor : curr.neighbors) {
//                 if (!res.contains(neighbor)) {
//                     queue.add(neighbor);
//                 }
//             }
//             res.add(curr);
//         }
        
//         return res;
//     }
// }


class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        
        Map<Node, Node> mapping = new HashMap<>();
        Node newNode = new Node();
        newNode.val = node.val;
        mapping.put(node, newNode);
        
        while (!queue.isEmpty()) {
            Node curr = queue.remove();
            List<Node> newNeighbors = new ArrayList<>();
            
            for (Node neighbor : curr.neighbors) {
                if (!mapping.containsKey(neighbor)) {
                    queue.add(neighbor);
                    
                    Node newNeighbor = new Node();
                    newNeighbor.val = neighbor.val;
                    mapping.put(neighbor, newNeighbor);
                }
                
                newNeighbors.add(mapping.get(neighbor));
            }
            
            mapping.get(curr).neighbors = newNeighbors;
        }    
        
        return mapping.get(node);
    }
}
