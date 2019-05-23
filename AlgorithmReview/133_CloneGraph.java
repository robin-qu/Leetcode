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
        
//         Queue<Node> toVisit = new LinkedList<>();
//         toVisit.offer(node);
//         Set<Node> visited = new HashSet<>();
//         Map<Node, Node> mapping = new HashMap<>();
        
//         // clone nodes
//         while (!toVisit.isEmpty()) {
//             Node curr = toVisit.poll();
//             mapping.put(curr, new Node(curr.val, new ArrayList<>()));
//             visited.add(curr);
            
//             for (Node neighbor : curr.neighbors) {
//                 if (!visited.contains(neighbor)) {
//                     toVisit.offer(neighbor);
//                 }
//             }
//         }
        
//         // clone edges
//         for (Node n : mapping.keySet()) {
//             for (Node neighbor : n.neighbors) {
//                 mapping.get(n).neighbors.add(mapping.get(neighbor));
//             }
//         }
        
//         return mapping.get(node);
//     }
// } 


// combine two iteration
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        
        Queue<Node> toVisit = new LinkedList<>();
        toVisit.offer(node);
        Map<Node, Node> mapping = new HashMap<>();
        mapping.put(node, new Node(node.val, new ArrayList<>()));
        
        // clone nodes
        while (!toVisit.isEmpty()) {
            Node curr = toVisit.poll();
            
            for (Node neighbor : curr.neighbors) {
                // check if the neighbor is visited
                if (!mapping.containsKey(neighbor)) {
                    toVisit.offer(neighbor);
                    mapping.put(neighbor, 
                                new Node(neighbor.val, new ArrayList<>()));
                }
                
                // clone edges
                mapping.get(curr).neighbors.add(mapping.get(neighbor));
            }
        }
        
        return mapping.get(node);
    }
} 