// // Brute force O(min(m, n)*m^2*n^2)
// public class Solution {
//     /**
//      * @param matrix: a matrix of 0 and 1
//      * @return: an integer
//      */
//     public int maxSquare(int[][] matrix) {
//         if (matrix == null || matrix.length == 0 ||
//             matrix[0] == null || matrix[0].length == 0) {
//             return 0;
//         }
        
//         int m = matrix.length;
//         int n = matrix[0].length;
//         int max = 0;
        
//         for (int size = Math.min(m, n); size >= 0; size--) {
//             for (int i = 0; i <= m - size; i++) {
//                 for (int j = 0; j <= n - size; j++) {
//                     if (isSquare(i, j, size, matrix)) {
//                         return size * size;
//                     }
//                 }
//             }
//         }
        
//         return 0;
//     }
    
//     private boolean isSquare(int row, int col, int size, int[][] matrix) {
//         for (int i = row; i < row + size; i++) {
//             for (int j = col; j < col + size; j++) {
//                 if (matrix[i][j] != 1) {
//                     return false;
//                 }
//             }
//         }
        
//         return true;
//     }
// }


// // DP time: O(mn) space: O(mn)
// public class Solution {
//     /**
//      * @param matrix: a matrix of 0 and 1
//      * @return: an integer
//      */
//     public int maxSquare(int[][] matrix) {
//         if (matrix == null || matrix.length == 0 ||
//             matrix[0] == null || matrix[0].length == 0) {
//             return 0;
//         }
        
//         int m = matrix.length;
//         int n = matrix[0].length;
//         int[][] dp = new int[m][n];  // dp[i][j] represents the maximum length of square whose bottom right corner's coordinate is (m, n)
//         int max = 0;
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (matrix[i][j] == 0) {
//                     dp[i][j] = 0;
//                     continue;
//                 }
                
//                 if (i == 0 || j == 0) {
//                     dp[i][j] = 1;
//                 } else {
//                     dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
//                 }
                
//                 max = Math.max(max, dp[i][j] * dp[i][j]);
//             }
//         }
        
//         return max;
//     }
// }


// Space optimized DP: time: O(mn) space: O(n) (rolling array)
public class Solution {
    /**
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    public int maxSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[2][n];  // dp[i][j] represents the maximum length of square whose bottom right corner's coordinate is (m, n)
        int max = 0;
        int old = 0;
        int now;
        for (int i = 0; i < m; i++) {
            now = old;
            old = 1 - now;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    dp[now][j] = 0;
                    continue;
                }
                
                if (i == 0 || j == 0) {
                    dp[now][j] = 1;
                } else {
                    dp[now][j] = 1 + Math.min(dp[old][j - 1], Math.min(dp[now][j - 1], dp[old][j]));
                }
                
                max = Math.max(max, dp[now][j] * dp[now][j]);
            }
        }
        
        return max;
    }
}