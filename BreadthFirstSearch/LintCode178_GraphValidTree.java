// // BFS
// public class Solution {
//     /**
//      * @param n: An integer
//      * @param edges: a list of undirected edges
//      * @return: true if it's a valid tree, or false
//      */
//     public boolean validTree(int n, int[][] edges) {
//         if (n == 0) {
//             return false;
//         }
        
//         if (edges.length == 0) {
//             return n == 1;
//         }
        
//         if (edges.length != n - 1) {
//             return false;
//         }
        
//         Map<Integer, Set<Integer>> graph = buildGraph(edges);
//         Set<Integer> visited = new HashSet<>();
//         Queue<Integer> toVisit = new LinkedList<>();
        
//         toVisit.add(edges[0][0]);
//         while (!toVisit.isEmpty()) {
//             int curr = toVisit.remove();
//             for (int neighbor : graph.get(curr)) {
//                 if (!visited.contains(neighbor)) {
//                     toVisit.add(neighbor);
//                 }
//             }
//             visited.add(curr);
//         }
        
//         return visited.size() == n;
//     }
    
//     private Map<Integer, Set<Integer>> buildGraph(int[][] edges) {
//         Map<Integer, Set<Integer>> graph = new HashMap<>();
        
//         for (int[] edge : edges) {
//             int vertex1 = edge[0];
//             int vertex2 = edge[1];
            
//             if (graph.containsKey(vertex1)) {
//                 graph.get(vertex1).add(vertex2);
//             } else {
//                 Set<Integer> neighbor = new HashSet<Integer>();
//                 neighbor.add(vertex2);
//                 graph.put(vertex1, neighbor);
//             }
            
//             if (graph.containsKey(vertex2)) {
//                 graph.get(vertex2).add(vertex1);
//             } else {
//                 Set<Integer> neighbor = new HashSet<Integer>();
//                 neighbor.add(vertex1);
//                 graph.put(vertex2, neighbor);
//             }
//         }
        
//         return graph;
//     }
// }

// DFS
public class Solution {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) {
            return false;
        }
        
        if (edges.length == 0) {
            return n == 1;
        }
        
        if (edges.length != n - 1) {
            return false;
        }
        
        Map<Integer, Set<Integer>> graph = buildGraph(edges);
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> toVisit = new Stack<>();
        
        toVisit.push(edges[0][0]);
        while (!toVisit.isEmpty()) {
            int curr = toVisit.pop();
            for (int neighbor : graph.get(curr)) {
                if (!visited.contains(neighbor)) {
                    toVisit.push(neighbor);
                }
            }
            visited.add(curr);
        }
        
        return visited.size() == n;
    }
    
    private Map<Integer, Set<Integer>> buildGraph(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        for (int[] edge : edges) {
            int vertex1 = edge[0];
            int vertex2 = edge[1];
            
            if (graph.containsKey(vertex1)) {
                graph.get(vertex1).add(vertex2);
            } else {
                Set<Integer> neighbor = new HashSet<Integer>();
                neighbor.add(vertex2);
                graph.put(vertex1, neighbor);
            }
            
            if (graph.containsKey(vertex2)) {
                graph.get(vertex2).add(vertex1);
            } else {
                Set<Integer> neighbor = new HashSet<Integer>();
                neighbor.add(vertex1);
                graph.put(vertex2, neighbor);
            }
        }
        
        return graph;
    }
}