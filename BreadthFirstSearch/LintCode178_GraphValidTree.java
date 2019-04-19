// BFS
public class Solution {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (n == 0 || edges.length != n - 1) {
            return false;
        }
        
        if (edges.length == 0) {
            return n == 1;
        }
        
        Map<Integer, Set<Integer>> graph = buildGraph(edges);
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> toVisit = new LinkedList<>();
        
        toVisit.add(edges[0][0]);
        while (!toVisit.isEmpty()) {
            int curr = toVisit.remove();
            for (int neighbor : graph.get(curr)) {
                if (!visited.contains(neighbor)) {
                    toVisit.add(neighbor);
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
            
            graph.putIfAbsent(vertex1, new HashSet<Integer>());
            graph.get(vertex1).add(vertex2);
            
            graph.putIfAbsent(vertex2, new HashSet<Integer>());
            graph.get(vertex2).add(vertex1);
        }
        
        return graph;
    }
}