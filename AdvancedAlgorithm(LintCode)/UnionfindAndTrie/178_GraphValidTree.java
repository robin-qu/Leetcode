// // basic graph approach
// public class Solution {
//     /**
//      * @param n: An integer
//      * @param edges: a list of undirected edges
//      * @return: true if it's a valid tree, or false
//      */
//     public boolean validTree(int n, int[][] edges) {
//         if (n < 1 || edges == null || edges.length != n - 1) {
//             return false;
//         }
        
//         boolean[] visited = new boolean[n];
//         int visitNum = 0;
        
//         Map<Integer, Set<Integer>> graph = buildGraph(n, edges);
        
//         Queue<Integer> toVisit = new LinkedList<>();
//         toVisit.offer(0);
        
//         while (!toVisit.isEmpty()) {
//             int curr = toVisit.poll();
//             visited[curr] = true;
//             visitNum++;
            
//             for (int neighbor : graph.get(curr)) {
//                 if (!visited[neighbor]) {
//                     toVisit.offer(neighbor);
//                 }
//             }
//         }
        
//         return visitNum == n;
//     }
    
    
//     private Map<Integer, Set<Integer>> buildGraph(int n, int[][] edges) {
//         Map<Integer, Set<Integer>> graph = new HashMap<>();
//         for (int i = 0; i < n; i++) {
//             graph.put(i, new HashSet<Integer>());
//         }
        
//         for (int[] edge : edges) {
//             graph.get(edge[0]).add(edge[1]);
//             graph.get(edge[1]).add(edge[0]);
//         }
        
//         return graph;
//     }
// }

// union find
public class Solution {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (n < 1 || edges == null || edges.length != n - 1) {
            return false;
        }
        
        int count = n;
        int[] graph = new int[n];
        for (int i = 0; i < n; i++) {
            graph[i] = i;
        }
        
        for (int i = 0; i < edges.length; i++) {
            count = connect(edges[i][0], edges[i][1], graph, count);
        }
        
        return count == 1;
    }
    
    private int find(int a, int[] graph) {
        int root = a;
        while (graph[root] != root) {
            root = graph[root];
        }
        
        int i = a;
        while (i != root) {
            int parent = graph[i];
            graph[i] = root;
            i = parent;
        }
        
        return root;
    }
    
    private int connect(int a, int b, int[] graph, int count) {
        int rootA = find(a, graph);
        int rootB = find(b, graph);
        if (rootA != rootB) {
            graph[rootA] = rootB;
            count--;
        }
        
        return count;
    }
}