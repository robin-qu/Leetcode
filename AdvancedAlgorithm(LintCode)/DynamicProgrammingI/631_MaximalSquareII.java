// // Initial version
// public class Solution {
//     /**
//      * @param matrix: a matrix of 0 an 1
//      * @return: an integer
//      */
//     public int maxSquare2(int[][] matrix) {
//         if (matrix == null || matrix.length == 0 ||
//             matrix[0] == null || matrix[0].length == 0) {
//             return 0;
//         } 
        
//         int m = matrix.length;
//         int n = matrix[0].length;
//         int[][] dp = new int[m][n];
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
//                     int len = dp[i - 1][j - 1];
//                     int up = 0;
//                     for (int k = 1; k <= len; k++) {
//                         if (matrix[i - k][j] == 0) {
//                             up++;
//                         } else {
//                             break;
//                         }
//                     }
//                     int left = 0;
//                     for (int k = 1; k <= len; k++) {
//                         if (matrix[i][j - k] == 0) {
//                             left++;
//                         } else {
//                             break;
//                         }
//                     }
//                     dp[i][j] = 1 + Math.min(len, Math.min(up, left));
//                 }
                
//                 max = Math.max(max, dp[i][j] * dp[i][j]);
//             }
//         }
        
//         return max;
//     }
// }


public class Solution {
    /**
     * @param matrix: a matrix of 0 an 1
     * @return: an integer
     */
    public int maxSquare2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return 0;
        } 
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int[][] left = new int[m][n]; // longest 0's on the left
        int[][] up = new int[m][n];  // longest 0's at the top
        int max = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                    left[i][j] = 1;
                    up[i][j] = 1;
                    
                    if (i > 0) {
                        up[i][j] += up[i - 1][j];
                    }
                    
                    if (j > 0) {
                        left[i][j] += left[i][j - 1];
                    }
                } else {
                    left[i][j] = 0;
                    up[i][j] = 0;
                    
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(up[i - 1][j], left[i][j - 1]));
                    }
                }
                
                max = Math.max(max, dp[i][j] * dp[i][j]);
            }
        }
        
        return max;
    }
}