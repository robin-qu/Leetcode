// // Union find O(n^2)time O(n)space
// class Solution {
//     private int[] uf;
//     private int count;
    
//     public int findCircleNum(int[][] M) {
//         if (M == null || M.length == 0 ||
//             M[0] == null || M[0].length == 0) {
//             return 0;
//         }
        
//         int n = M.length;
//         this.count = n;
//         this.uf = new int[n];
//         for (int i = 0; i < n; i++) {
//             uf[i] = i;
//         }
        
//         for (int i = 0; i < n; i++) {
//             for (int j = i + 1; j < n; j++) {
//                 if (M[i][j] == 1) {
//                     connect(i, j);
//                 }
//             }
//         }
        
//         return this.count;
//     }
    
//     private int find(int x) {
//         int root = x;
        
//         while (uf[root] != root) {
//             root = uf[root];
//         }
        
//         int curr = x;
//         while (uf[curr] != root) {
//             int parent = uf[curr];
//             uf[curr] = root;
//             curr = parent;
//         }
        
//         return root;
//     }
    
//     private void connect(int a, int b) {
//         int rootA = find(a);
//         int rootB = find(b);
        
//         if (rootA != rootB) {
//             uf[rootA] = rootB;
//             count--;
//         }
//     }
// }


// // dfs O(n^2)time O(n)space
// class Solution {
//     public int findCircleNum(int[][] M) {
//         if (M == null || M.length == 0 ||
//             M[0] == null || M[0].length == 0) {
//             return 0;
//         }
        
//         int n = M.length;
//         boolean[] visited = new boolean[n];
//         int count = 0;
        
//         for (int i = 0; i < n; i++) {
//             if (!visited[i]) {
//                 count++;
//                 dfs(i, visited, M);
//             }
//         }
        
//         return count;
//     }
    
//     private void dfs(int i, boolean[] visited, int[][] M) {
//         visited[i] = true;
        
//         for (int j = 0; j < visited.length; j++) {
//             if (M[i][j] == 1 && !visited[j]) {
//                 dfs(j, visited, M);
//             }
//         }
//     }
// } 


// dfs O(n^2)time O(n)space
class Solution {
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0 ||
            M[0] == null || M[0].length == 0) {
            return 0;
        }
        
        int n = M.length;
        boolean[] visited = new boolean[n];
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                bfs(i, visited, M);
            }
        }
        
        return count;
    }
    
    private void bfs(int idx, boolean[] visited, int[][] M) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(idx);
        visited[idx] = true;
        
        while (!queue.isEmpty()) {
            int i = queue.poll();
            
            for (int j = 0; j < visited.length; j++) {
                if (M[i][j] == 1 && !visited[j]) {
                    queue.offer(j);
                    visited[j] = true;
                }
            }
        }
    }
} 