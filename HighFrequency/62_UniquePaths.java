// // dp O(mn)time O(mn)space
// class Solution {
//     public int uniquePaths(int m, int n) {
//         if (m <= 0 || n <= 0) {
//             return 0;
//         }
        
//         int[][] dp = new int[m][n];
        
//         for (int i = 0; i < n; i++) {
//             dp[0][i] = 1;
//         }
        
//         for (int i = 1; i < m; i++) {
//             dp[i][0] = 1;
//             for (int j = 1; j < n; j++) {
//                 dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
//             }
//         }
        
//         return dp[m - 1][n - 1];
//     }
// }


// dp O(mn)time O(n)space
class Solution {
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        
        int[] dp = new int[n];
        
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        
        for (int i = 1; i < m; i++) {
            dp[0] = 1;
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        
        return dp[n - 1];
    }
}