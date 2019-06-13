public class ConnectingGraph2 {
    /*
    * @param n: An integer
    */
    private int[] graph;
    private int[] count;
    
    public ConnectingGraph2(int n) {
        this.graph = new int[n + 1];  // 1~n
        this.count = new int[n + 1];  // 1~n
        for (int i = 0; i <= n; i++) {
            graph[i] = i;
            count[i] = 1;
        }
    }
    
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
        if (rootA != rootB) {
            graph[rootA] = rootB;
            count[rootB] += count[rootA];
        }
    }

    /*
     * @param a: An integer
     * @return: An integer
     */
    public int query(int a) {
        int rootA = find(a);
        return count[rootA];
    }
}