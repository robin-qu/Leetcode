// // Brute force
// public class Solution {
//     /**
//      * @param grid: Given a 2D grid, each cell is either 'W', 'E' or '0'
//      * @return: an integer, the maximum enemies you can kill using one bomb
//      */
    
//     public static final int[] dx = new int[]{0, 1, -1, 0};
//     public static final int[] dy = new int[]{1, 0, 0, -1};
    
//     public int maxKilledEnemies(char[][] grid) {
//         if (grid == null || grid.length == 0 ||
//             grid[0] == null || grid[0].length == 0) {
//             return 0;
//         }
        
//         int m = grid.length;
//         int n = grid[0].length;
        
//         int res = 0;
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (grid[i][j] == '0') {
//                     res = Math.max(res, kill(grid, i, j, m, n));
//                 }
//             }
//         }
        
//         return res;
//     }
    
//     private int kill(char[][] grid, int x, int y, int m, int n) {
//         int res = 0;
        
//         for (int i = 0; i < 4; i++) {
//             int nx = x;
//             int ny = y;
            
//             while (valid(nx + dx[i], ny + dy[i], m, n, grid)) {
//                 nx += dx[i];
//                 ny += dy[i];
//                 if (grid[nx][ny] == 'E') {
//                     res++;
//                 }
//             }
//         }
        
//         return res;
//     }
    
//     private boolean valid(int i, int j, int m, int n, char[][] grid) {
//         return i >= 0 && i < m && j >= 0 && j < n && grid[i][j] != 'W';
//     }
// }


// // DP
// public class Solution {
//     /**
//      * @param grid: Given a 2D grid, each cell is either 'W', 'E' or '0'
//      * @return: an integer, the maximum enemies you can kill using one bomb
//      */
//     public int maxKilledEnemies(char[][] grid) {
//         if (grid == null || grid.length == 0 ||
//             grid[0] == null || grid[0].length == 0) {
//             return 0;
//         }
        
//         int m = grid.length;
//         int n = grid[0].length;
        
//         // XX[i][j] represents the number of enemies can be killed in XX direction
//         int[][] up = new int[m][n];  
//         int[][] down = new int[m][n];
//         int[][] left = new int[m][n];
//         int[][] right = new int[m][n];
        
//         // up
//         for (int i = 1; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (grid[i - 1][j] == 'W') {
//                     up[i][j] = 0;
//                 } else if (grid[i - 1][j] == 'E') {
//                     up[i][j] = 1 + up[i - 1][j];
//                 } else {
//                     up[i][j] = up[i - 1][j];
//                 }
//             }
//         }
        
//         // down
//         for (int i = m - 2; i >= 0; i--) {
//             for (int j = 0; j < n; j++) {
//                 if (grid[i + 1][j] == 'W') {
//                     down[i][j] = 0;
//                 } else if (grid[i + 1][j] == 'E') {
//                     down[i][j] = 1 + down[i + 1][j];
//                 } else {
//                     down[i][j] = down[i + 1][j];
//                 }
//             }
//         }
        
//         // left
//         for (int j = 1; j < n; j++) {
//             for (int i = 0; i < m; i++) {
//                 if (grid[i][j - 1] == 'W') {
//                     left[i][j] = 0;
//                 } else if (grid[i][j - 1] == 'E') {
//                     left[i][j] = 1 + left[i][j - 1];
//                 } else {
//                     left[i][j] = left[i][j - 1];
//                 }
//             }
//         }
        
//         // right
//         for (int j = n - 2; j >= 0; j--) {
//             for (int i = 0; i < m; i++) {
//                 if (grid[i][j + 1] == 'W') {
//                     right[i][j] = 0;
//                 } else if (grid[i][j + 1] == 'E') {
//                     right[i][j] = 1 + right[i][j + 1];
//                 } else {
//                     right[i][j] = right[i][j + 1];
//                 }
//             }
//         }
        
//         int res = 0;
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (grid[i][j] == '0') {
//                     res = Math.max(res, up[i][j] + down[i][j] + left[i][j] + right[i][j]);
//                 }
//             }
//         }
        
//         return res;
//     }
// }


// Optimized DP
public class Solution {
    /**
     * @param grid Given a 2D grid, each cell is either 'W', 'E' or '0'
     * @return an integer, the maximum enemies you can kill using one bomb
     */
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 ||
            grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;

        int res = 0;
        int rows = 0; // store the number of enemies that can be killed in the current row
        int[] cols = new int[n]; // store the number of enemies that can be killed in each column
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || grid[i][j-1] == 'W') {  // upper boundary
                    rows = 0;
                    for (int k = j; k < n && grid[i][k] != 'W'; ++k)
                        if (grid[i][k] == 'E')
                            rows += 1;  // find the total number until hit the lower boundary
                    
                }
                if (i == 0 || grid[i-1][j] == 'W') {  // left boundary
                    cols[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'W'; ++k)
                        if (grid[k][j] == 'E')
                            cols[j] += 1;  // find the total numebr until hit the right boundary
                }

                if (grid[i][j] == '0')
                    res = Math.max(res, rows + cols[j]);
            }
        }
        
        return res;
    }
}