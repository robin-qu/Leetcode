// class Solution {
//     public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//         if (obstacleGrid == null) {
//             return 0;
//         }
        
//         int m = obstacleGrid.length;
//         int n = obstacleGrid[0].length;
        
//         if (m == 0 || n == 0) {
//             return 0;
//         }
        
//         int [][] dp = new int[m][n];
        
//         if (obstacleGrid[0][0] == 1) {
//             return 0;
//         }
        
//         dp[0][0] = 1;
        
//         // Initialize first column
//         for (int i = 1; i < m; i++) {
//             if (obstacleGrid[i][0] == 1) {
//                 dp[i][0] = 0;
//             } else {
//                 dp[i][0] = dp[i - 1][0];
//             }
//         }
        
//         // Initialize first row
//         for (int i = 1; i < n; i++) {
//             if (obstacleGrid[0][i] == 1) {
//                 dp[0][i] = 0;
//             } else {
//                 dp[0][i] = dp[0][i - 1];
//             }
//         }
        
//         for (int i = 1; i < m; i++) {
//             for (int j = 1; j < n; j++) {
//                 if (obstacleGrid[i][j] == 1) {
//                     dp[i][j] = 0;
//                 } else {
//                     dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
//                 }
//             }
//         }
        
//         return dp[m - 1][n - 1];
//     }
// }


// Optimized - less memory complexity
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null) {
            return 0;
        }
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        if (m == 0 || n == 0) {
            return 0;
        }
        
        int [] dp = new int[n];
        
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        
        dp[0] = 1;
        
        // Initialize first row
        for (int i = 1; i < n; i++) {
            dp[i] = obstacleGrid[0][i] == 1 ? 0 : dp[i - 1];
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    dp[j] = obstacleGrid[i][j] == 1 ? 0 : dp[j];
                } else {
                    dp[j] = obstacleGrid[i][j] == 1 ? 0 : dp[j - 1] + dp[j];
                }
            }
        }
        
        return dp[n - 1];
    }
}