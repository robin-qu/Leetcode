// // BFS O(mn)time O(mn)space
// class Solution {
//     class Pair {
//         int x;
//         int y;
        
//         public Pair(int x, int y) {
//             this.x = x;
//             this.y = y;
//         }
//     }
    
//     public static final char LAND = '1';
//     public static final char WATER = '0';
//     public static final int[] dx = new int[]{0, 1, -1, 0};
//     public static final int[] dy = new int[]{1, 0, 0, -1};
    
//     public int numIslands(char[][] grid) {
//         if (grid == null || grid.length == 0 ||
//             grid[0] == null || grid[0].length == 0) {
//             return 0;
//         }
        
//         int m = grid.length;
//         int n = grid[0].length;
//         boolean[][] visited = new boolean[m][n];
//         int count = 0;
        
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (!visited[i][j] && grid[i][j] == LAND) {
//                     count++;
//                     bfs(i, j, m, n, grid, visited);
//                 }
//             }
//         }
        
//         return count;
//     }
    
//     private void bfs(int x, int y, int m, int n, char[][] grid, boolean[][] visited) {
//         Queue<Pair> queue = new LinkedList<>();
//         queue.offer(new Pair(x, y));
//         visited[x][y] = true;
        
//         while (!queue.isEmpty()) {
//             Pair curr = queue.poll();
            
//             for (int i = 0; i < 4; i++) {
//                 int nx = curr.x + dx[i];
//                 int ny = curr.y + dy[i];
                
//                 if (inBound(nx, ny, m, n) && !visited[nx][ny]) {
//                     visited[nx][ny] = true;
//                     if (grid[nx][ny] == LAND) {
//                         queue.offer(new Pair(nx, ny));
//                     }
//                 }
//             }
//         }
//     }
    
//     private boolean inBound(int x, int y, int m, int n) {
//         return x >= 0 && x < m && y >= 0 && y < n;
//     }
// }


// Union Find O(mn)time O(mn)space
class Solution {
    public static final char LAND = '1';
    public static final char WATER = '0';
    public static final int[] dx = new int[]{0, 1, -1, 0};
    public static final int[] dy = new int[]{1, 0, 0, -1};
    public int[] parent;
    public int count;
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 ||
            grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        this.parent = new int[m * n];
        this.count = 0;
        
        // initialization
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == LAND) {
                    parent[i * n + j] = i * n + j;
                    count++;
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == LAND) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (inBound(nx, ny, m, n) && grid[nx][ny] == LAND) {
                            connect(i * n + j, nx * n + ny);
                        }
                    }
                }
            }
        }
        
        return count;
    }
    
    private int find(int p) {
        int root = p;
        
        while (parent[root] != root) {
            root = parent[root];
        }
        
        int pi = p;
        while (pi != root) {
            int temp = parent[pi];
            parent[pi] = root;
            pi = temp;
        }
        
        return root;
    }
    
    private void connect(int p1, int p2) {
        int root1 = find(p1);
        int root2 = find(p2);
        if (root1 != root2) {
            parent[root1] = root2;
            count--;
        }
    }
    
    private boolean inBound(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}