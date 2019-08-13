// // DP O(mn)time O(mn)space
// class Solution {
//     public int maximalSquare(char[][] matrix) {
//         if (matrix == null || matrix.length == 0 ||
//             matrix[0] == null || matrix[0].length == 0) {
//             return 0;
//         }
        
//         int m = matrix.length;
//         int n = matrix[0].length;
//         int[][] dp = new int[m + 1][n + 1];  // dp[i][j] represents the area of maximal square whose right bottom corner is matrix[i - 1][j - 1]
        
//         int max = 0;
//         for (int i = 1; i <= m; i++) {
//             for (int j = 1; j <= n; j++) {
//                 if (matrix[i - 1][j - 1] == '0') {
//                     dp[i][j] = 0;
//                 } else {
//                     dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
//                 }
                
//                 max = Math.max(max, dp[i][j] * dp[i][j]);
//             }
//         }
        
//         return max;
//     }
// }


// // DP O(mn)time O(n)space
// class Solution {
//     public int maximalSquare(char[][] matrix) {
//         if (matrix == null || matrix.length == 0 ||
//             matrix[0] == null || matrix[0].length == 0) {
//             return 0;
//         }
        
//         int m = matrix.length;
//         int n = matrix[0].length;
//         int[][] dp = new int[2][n + 1];  // dp[i][j] represents the area of maximal square whose right bottom corner is matrix[i - 1][j - 1]
        
//         int max = 0;
//         int now = 0;
//         int old = 1;
//         for (int i = 1; i <= m; i++) {
//             old = now;
//             now = 1 - now;
//             for (int j = 1; j <= n; j++) {
//                 if (matrix[i - 1][j - 1] == '0') {
//                     dp[now][j] = 0;
//                 } else {
//                     dp[now][j] = 1 + Math.min(dp[old][j - 1], Math.min(dp[old][j], dp[now][j - 1]));
//                 }
                
//                 max = Math.max(max, dp[now][j] * dp[now][j]);
//             }
//         }
        
//         return max;
//     }
// }


// DP O(mn)time O(n)space 1D array
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dp = new int[n + 1];  // dp[j] represents the area of maximal square whose right bottom corner is matrix[i - 1][j - 1]
        
        int max = 0;
        int prev = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '0') {
                    dp[j] = 0;
                } else {
                    dp[j] = 1 + Math.min(prev, Math.min(dp[j - 1], dp[j]));
                }
                prev = temp;
                
                max = Math.max(max, dp[j] * dp[j]);
            }
        }
        
        return max;
    }
}