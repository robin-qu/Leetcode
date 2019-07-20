// // bfs(Memory Limit Exceeded) O(mn(m+n))time O(mn)space
// public class Solution {
//     /**
//      * @param grid: a 2D grid
//      * @return: An integer
//      */
//     public static final int HOUSE = 1;
//     public static final int EMPTY = 0;
//     public static final int[] dx = new int[]{1, 0, 0, -1};
//     public static final int[] dy = new int[]{0, 1, -1, 0};
    
//     class Pair {
//         public int x;
//         public int y;
        
//         public Pair(int x, int y) {
//             this.x = x;
//             this.y = y;
//         }
//     }
    
//     public int shortestDistance(int[][] grid) {
//         if (grid == null || grid.length == 0 ||
//             grid[0] == null || grid[0].length == 0) {
//             return -1;
//         }
        
//         int m = grid.length;
//         int n = grid[0].length;
//         int[][] dist = new int[m][n];
        
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (grid[i][j] == HOUSE) {
//                     bfs(i, j, grid, dist);
//                 }
//             }
//         }
        
//         int res = Integer.MAX_VALUE;
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (grid[i][j] == EMPTY) {
//                     res = Math.min(res, dist[i][j]);
//                 }
//             }
//         }
        
//         return res == Integer.MAX_VALUE ? -1 : res;
//     }
    
//     private void bfs(int x, int y, int[][] grid, int[][] dist) {
//         int m = grid.length;
//         int n = grid[0].length;
//         Queue<Pair> queue = new LinkedList<>();
//         queue.offer(new Pair(x, y));
//         boolean[][] visited = new boolean[m][n];
//         visited[x][y] = true;
//         int level = 1;
        
//         while (!queue.isEmpty()) {
//             int size = queue.size();
            
//             for (int i = 0; i < size; i++) {
//                 Pair curr = queue.poll();
                
//                 for (int k = 0; k < 4; k++) {
//                     int nx = curr.x + dx[k];
//                     int ny = curr.y + dy[k];
//                     if (valid(nx, ny, m, n, visited)) {
//                         dist[nx][ny] += level;
//                         visited[nx][ny] = true;
//                         queue.offer(new Pair(nx, ny));
//                     }
//                 }
//             }
                
//             level++;
//         }
//     }
    
//     private boolean valid(int x, int y, int m, int n, boolean[][] visited) {
//         return x >= 0 && x < m && y >= 0 && y < n && !visited[x][y];
//     }
// }

// O(mn)time O(m + n)space
public class Solution {
    /*
     * @param grid: a 2D grid
     * @return: An integer
     */
    public static final int HOUSE = 1;
    public static final int EMPTY = 0;
    
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 ||
            grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        
        int[] rowCount = new int[m];  // the number of houses in that row
        int[] colCount = new int[n];  // the numerb fo house in that column
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == HOUSE) {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }
        
        int[] rowDist = getDist(rowCount);  // the total distance of all of the houses on each row with the current row
        int[] colDist = getDist(colCount);  // the total distance of all of the houses on each column with the current column
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == EMPTY) {
                    res = Math.min(res, rowDist[i] + colDist[j]);
                }
            }
        }
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    private int[] getDist(int[] count) {
        int n = count.length;
        int[] res = new int[n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i] += count[j] * Math.abs(i - j);
            }
        }
        
        return res;
    }
}