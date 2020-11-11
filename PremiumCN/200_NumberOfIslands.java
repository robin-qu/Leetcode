// class Solution {
//     private static final int[] dx = {1, 0, 0, -1};
//     private static final int[] dy = {0, 1, -1, 0};
                                            
//     public int numIslands(char[][] grid) {
//         if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
//             return 0;
//         }
        
//         int m = grid.length;
//         int n = grid[0].length;
//         int res = 0;
//         boolean[][] visited = new boolean[m][n];
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (visited[i][j] || grid[i][j] == '0') {
//                     continue;
//                 }
//                 bfs(i, j, grid, visited);
//                 res++;
//             }
//         }
        
//         return res;
//     }
                                        
//     private void bfs(int x, int y, char[][] grid, boolean[][] visited) {
//         Queue<int[]> queue = new LinkedList<>();
//         queue.offer(new int[] {x, y});
//         visited[x][y] = true;
        
//         while (!queue.isEmpty()) {
//             int[] curr = queue.poll();
            
//             for (int i = 0; i < 4; i++) {
//                 int nx = curr[0] + dx[i];
//                 int ny = curr[1] + dy[i];
//                 if (inBound(nx, ny, grid) && !visited[nx][ny] && grid[nx][ny] == '1') {
//                     queue.offer(new int[] {nx, ny});
//                     visited[nx][ny] = true;
//                 }
//             }
//         }
//     }
                                        
//     private boolean inBound(int x, int y, char[][] grid) {
//         return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
//     }
// }


class Solution {
    private static final int[] dx = {1, 0, 0, -1};
    private static final int[] dy = {0, 1, -1, 0};
                                            
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        char[][] copy = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = grid[i][j];
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] == '1') {
                    dfs(i, j, copy);
                    res++;
                }
            }
        }
        
        return res;
    }
                                        
    private void dfs(int x, int y, char[][] grid) {
        grid[x][y] = '#';
        
        for (int i = 0 ; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (inBound(nx, ny, grid) && grid[nx][ny] == '1') {
                dfs(nx, ny, grid);
            }
        }
    }
                                        
    private boolean inBound(int x, int y, char[][] grid) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }
}