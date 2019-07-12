// // dp O(mn)time O(mn)space
// public class Solution {
//     /**
//      * @param nums: an integer array and all positive numbers
//      * @param target: An integer
//      * @return: An integer
//      */
//     public int backPackV(int[] A, int m) {
//         if (A == null || A.length == 0 || m <= 0) {
//             return 0;
//         }
        
//         int n = A.length;
        
//         int[][] dp = new int[n + 1][m + 1];  // dp[i][j] represents the number of possible fill of the first i items and size j of the backpack
        
//         dp[0][0] = 1;
//         for (int i = 1; i <= m; i++) {
//             dp[0][i] = 0;
//         }
        
//         for (int i = 1; i <= n; i++) {
//             for (int j = 0; j <= m; j++) {
//                 dp[i][j] = dp[i - 1][j];  // item i does not go into backpack
                    
//                 if (A[i - 1] <= j) {
//                     dp[i][j] = dp[i - 1][j] + dp[i - 1][j - A[i - 1]];
//                 }
//             }
//         }
        
//         return dp[n][m];
//     }
// }


// // dp O(mn)time O(m)space
// public class Solution {
//     /**
//      * @param nums: an integer array and all positive numbers
//      * @param target: An integer
//      * @return: An integer
//      */
//     public int backPackV(int[] A, int m) {
//         if (A == null || A.length == 0 || m <= 0) {
//             return 0;
//         }
        
//         int n = A.length;
        
//         int[][] dp = new int[2][m + 1];  // dp[i][j] represents the number of possible fill of the first i items and size j of the backpack
        
//         dp[0][0] = 1;
//         for (int i = 1; i <= m; i++) {
//             dp[0][i] = 0;
//         }
        
//         int old = 1;
//         int now = 0;
//         for (int i = 1; i <= n; i++) {
//             old = now;
//             now = 1 - now;
//             for (int j = 0; j <= m; j++) {
//                 dp[now][j] = dp[old][j];  // item i does not go into backpack
                    
//                 if (A[i - 1] <= j) {
//                     dp[now][j] = dp[old][j] + dp[old][j - A[i - 1]];
//                 }
//             }
//         }
        
//         return dp[now][m];
//     }
// }


// dp O(mn)time O(m)space  1D array
public class Solution {
    /**
     * @param nums: an integer array and all positive numbers
     * @param target: An integer
     * @return: An integer
     */
    public int backPackV(int[] A, int m) {
        if (A == null || A.length == 0 || m <= 0) {
            return 0;
        }
        
        int n = A.length;
        
        int[] dp = new int[m + 1];  // dp[i][j] represents the number of possible fill of the first i items and size j of the backpack
        
        dp[0] = 1;
        for (int i = 1; i <= m; i++) {
            dp[i] = 0;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= A[i - 1]; j--) {
                dp[j] += dp[j - A[i - 1]];
            }
        }
        
        return dp[m];
    }
}