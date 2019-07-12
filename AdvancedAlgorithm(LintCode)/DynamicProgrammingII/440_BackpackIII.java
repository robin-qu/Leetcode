// // DP O(nm^2) time O(nm) space
// public class Solution {
//     /**
//      * @param A: an integer array
//      * @param V: an integer array
//      * @param m: An integer
//      * @return: an array
//      */
//     public int backPackIII(int[] A, int[] V, int m) {
//         if (A == null || A.length == 0 ||
//             V == null || V.length == 0 || m <= 0) {
//             return 0;
//         }
        
//         int n = A.length;
//         int[][] dp = new int[n + 1][m + 1];  // dp[i][j] represents the maximum value of the first i kinds of items that can fill size j of the backpack
//         dp[0][0] = 0;
//         for (int i = 1; i <= m; i++) {
//             dp[0][i] = -1;
//         }
        
//         int max = 0;
//         for (int i = 1; i <= n; i++) {
//             for (int j = 0; j <= m; j++) {
//                 for (int k = 0; k * A[i - 1] <= j; k++) {
//                     if (dp[i - 1][j - k * A[i - 1]] != -1) {
//                         dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * A[i - 1]] + k * V[i - 1]);
//                     }
//                 }  // dp[i][j] = dp[i - 1][j] is included in the loop
                
//                 max = Math.max(max, dp[i][j]);
//             }
//         }
        
//         return max;
//     }
// }


// // DP O(nm) time O(nm) space
// public class Solution {
//     /**
//      * @param A: an integer array
//      * @param V: an integer array
//      * @param m: An integer
//      * @return: an array
//      */
//     public int backPackIII(int[] A, int[] V, int m) {
//         if (A == null || A.length == 0 ||
//             V == null || V.length == 0 || m <= 0) {
//             return 0;
//         }
        
//         int n = A.length;
//         int[][] dp = new int[n + 1][m + 1];  // dp[i][j] represents the maximum value of the first i kinds of items that can fill size j of the backpack
//         dp[0][0] = 0;
//         for (int i = 1; i <= m; i++) {
//             dp[0][i] = -1;
//         }
        
//         int max = 0;
//         for (int i = 1; i <= n; i++) {
//             for (int j = 0; j <= m; j++) {
//                 dp[i][j] = dp[i - 1][j];
                
//                 if (j >= A[i - 1] && dp[i][j - A[i - 1]] != -1) {
//                     dp[i][j] = Math.max(dp[i][j], dp[i][j - A[i - 1]] + V[i - 1]);
//                 }
                
//                 max = Math.max(max, dp[i][j]);
//             }
//         }
        
//         return max;
//     }
// }


// // DP O(nm) time O(m) space
// public class Solution {
//     /**
//      * @param A: an integer array
//      * @param V: an integer array
//      * @param m: An integer
//      * @return: an array
//      */
//     public int backPackIII(int[] A, int[] V, int m) {
//         if (A == null || A.length == 0 ||
//             V == null || V.length == 0 || m <= 0) {
//             return 0;
//         }
        
//         int n = A.length;
//         int[][] dp = new int[2][m + 1];  // dp[i][j] represents the maximum value of the first i kinds of items that can fill size j of the backpack
//         dp[0][0] = 0;
//         for (int i = 1; i <= m; i++) {
//             dp[0][i] = -1;
//         }
        
//         int max = 0;
//         int old = 1;
//         int now = 0;
//         for (int i = 1; i <= n; i++) {
//             old = now;
//             now = 1 - now;
//             for (int j = 0; j <= m; j++) {
//                 dp[now][j] = dp[old][j];
                
//                 if (j >= A[i - 1] && dp[now][j - A[i - 1]] != -1) {
//                     dp[now][j] = Math.max(dp[old][j], dp[now][j - A[i - 1]] + V[i - 1]);
//                 }
                
//                 max = Math.max(max, dp[now][j]);
//             }
//         }
        
//         return max;
//     }
// }


// DP O(nm) time O(m) space  1D array
public class Solution {
    /**
     * @param A: an integer array
     * @param V: an integer array
     * @param m: An integer
     * @return: an array
     */
    public int backPackIII(int[] A, int[] V, int m) {
        if (A == null || A.length == 0 ||
            V == null || V.length == 0 || m <= 0) {
            return 0;
        }
        
        int n = A.length;
        int[] dp = new int[m + 1];  // dp[i][j] represents the maximum value of the first i kinds of items that can fill size j of the backpack
        dp[0] = 0;
        for (int i = 1; i <= m; i++) {
            dp[i] = -1;
        }
        
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = A[i - 1]; j <= m; j++) {
                if (dp[j - A[i - 1]] != -1) {
                    dp[j] = Math.max(dp[j], dp[j - A[i - 1]] + V[i - 1]);
                }
                
                max = Math.max(max, dp[j]);
            }
        }
        
        return max;
    }
}