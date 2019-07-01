// public class Solution {
//     /**
//      * @param grid: a list of lists of integers
//      * @return: An integer, minimizes the sum of all numbers along its path
//      */
//     public int minPathSum(int[][] grid) {
//         if (grid == null || grid.length == 0 ||
//             grid[0] == null || grid[0].length == 0) {
//             return 0;
//         }
        
//         int m = grid.length;
//         int n = grid[0].length;
        
//         // Initialization:
//         int[] dp = new int[n];
//         dp[0] = grid[0][0];
//         for (int i = 1; i < n; i++) {
//             dp[i] = grid[0][i] + dp[i - 1];
//         }
        
//         for (int i = 1; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (j == 0) {
//                     dp[j] += grid[i][j];
//                 } else {
//                     dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
//                 }
//             }
//         }
        
//         return dp[n - 1];
//     }
// }


// Rolling array
public class Solution {
    /**
     * @param grid: a list of lists of integers
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 ||
            grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        
        // Initialization:
        int[][] dp = new int[2][n];  // rolling array
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        
        int old = 0;
        int now = 0;
        
        for (int i = 1; i < m; i++) {
            old = now;
            now = 1 - now;
            
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    dp[now][j] = dp[old][j] + grid[i][j];
                } else {
                    dp[now][j] = grid[i][j] + Math.min(dp[old][j], dp[now][j - 1]);
                }
            }
        }
        
        return dp[now][n - 1];
    }
}