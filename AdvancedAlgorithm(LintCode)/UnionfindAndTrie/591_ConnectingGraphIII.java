public class ConnectingGraph3 {
    /**
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    
    private int[] graph;
    private int num;
    
    public ConnectingGraph3(int n) {
        this.graph = new int[n + 1];
        for (int i = 0; i <= n ; i++) {
            graph[i] = i;
        }
        num = n;
    }
    
    // Finds the root of value a, performing
    // path compression along the way up
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
    
    public void connect(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            graph[rootA] = rootB;
            num--;
        }
    }

    /**
     * @return: An integer
     */
    public int query() {
        return this.num;
    }
}