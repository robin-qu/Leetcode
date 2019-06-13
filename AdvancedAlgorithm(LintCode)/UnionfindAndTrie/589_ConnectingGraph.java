public class ConnectingGraph {
    /*
    * @param n: An integer
    */
    public int[] graph;
    
    public ConnectingGraph(int n) {
        this.graph = new int[n + 1];  // 1 ~ n
        for (int i = 0; i <= n; i++) {
            graph[i] = i;
        }
    }
    
    // Finds the root of value a, performing path 
    // compression along the way up
    private int find(int a) {
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

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        graph[rootA] = rootB;
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: A boolean
     */
    public boolean query(int a, int b) {
        return find(a) == find(b);
    }
}