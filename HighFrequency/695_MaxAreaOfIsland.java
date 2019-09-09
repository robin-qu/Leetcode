// // Union find O(mn)time O(mn)space
// class Solution {
//     private static final int[] dx = new int[]{0, 1, -1, 0};
//     private static final int[] dy = new int[]{1, 0, 0, -1};
    
//     public int maxAreaOfIsland(int[][] grid) {
//         if (grid == null || grid.length == 0 ||
//             grid[0] == null || grid[0].length == 0) {
//             return 0;
//         }
        
//         int m = grid.length;
//         int n = grid[0].length;
        
//         int[] uf = new int[m * n];
//         int[] count = new int[m * n];
        
//         for (int i = 0; i < m * n; i++) {
//             uf[i] = i;
//             count[i] = grid[i / n][i % n];
//         }
        
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (grid[i][j] == 1) {
//                     for (int k = 0; k < 4; k++) {
//                         int nx = i + dx[k];
//                         int ny = j + dy[k];
                        
//                         if (inBound(nx, ny, m, n) && grid[nx][ny] == 1) {
//                             connect(i * n + j, nx * n + ny, uf, count);
//                         }
//                     }
//                 }
//             }
//         }
        
//         int max = 0;
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 max = Math.max(max, count[i * n + j]);
//             }
//         }
        
//         return max;
//     }
    
//     private int find(int x, int[] uf) {
//         int root = x;
//         while (uf[root] != root) {
//             root = uf[root];
//         }
        
//         int curr = x;
//         while (curr != root) {
//             int parent = uf[curr];
//             uf[curr] = root;
//             curr = parent;
//         }
        
//         return root;
//     }
    
//     private void connect(int a, int b, int[] uf, int[] count) {
//         int rootA = find(a, uf);
//     int rootB = find(b, uf);
        
//         if (rootA != rootB) {
//             uf[rootA] = rootB;
//             count[rootB] += count[rootA];
//         }
//     }
    
//     private boolean inBound(int x, int y, int m, int n) {
//         return x >= 0 && x < m && y >= 0 && y < n;
//     }
// }


// // dfs O(mn)time O(mn)space
// class Solution {
//     private static final int[] dx = new int[]{0, 1, -1, 0};
//     private static final int[] dy = new int[]{1, 0, 0, -1};
    
//     public int maxAreaOfIsland(int[][] grid) {
//         if (grid == null || grid.length == 0 ||
//             grid[0] == null || grid[0].length == 0) {
//             return 0;
//         }
        
//         int m = grid.length;
//         int n = grid[0].length;
//         boolean[][] visited = new boolean[m][n];
        
//         int max = 0;
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (!visited[i][j] && grid[i][j] == 1) {
//                     max = Math.max(max, dfs(i, j, grid, visited));
//                 }
//             }
//         }
        
//         return max;
//     }
    
//     private int dfs(int x, int y, int[][] grid, boolean[][] visited) {
//         int count = 1;
//         visited[x][y] = true;
        
//         int m = grid.length;
//         int n = grid[0].length;
        
//         for (int i = 0; i < 4; i++) {
//             int nx = x + dx[i];
//             int ny = y + dy[i];
            
//             if (inBound(nx, ny, m, n) && !visited[nx][ny] && grid[nx][ny] == 1) {
//                 count += dfs(nx, ny, grid, visited);
//             }
//         }
        
//         return count;
//     }
    
//     private boolean inBound(int x, int y, int m, int n) {
//         return x >= 0 && x < m && y >= 0 && y < n;
//     }
// }


// dfs O(mn)time O(mn)space
class Solution {
    private static final int[] dx = new int[]{0, 1, -1, 0};
    private static final int[] dy = new int[]{1, 0, 0, -1};
    
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 ||
            grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    max = Math.max(max, bfs(i, j, m, n, grid, visited));
                }
            }
        }
        
        return max;
    }
    
    private int bfs(int x, int y, int m, int n, int[][] grid, boolean[][] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x * n + y);
        visited[x][y] = true;
        int count = 0;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int currX = curr / n;
            int currY = curr % n;
            visited[currX][currY] = true;
            count++;
            
            for (int i = 0; i < 4; i++) {
                int nx = currX + dx[i];
                int ny = currY + dy[i];
                
                if (inBound(nx, ny, m, n) && !visited[nx][ny] && grid[nx][ny] == 1) {
                    queue.offer(nx * n + ny);
                    visited[nx][ny] = true;
                }
            }
        }
        
        return count;
    }
    
    private boolean inBound(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}