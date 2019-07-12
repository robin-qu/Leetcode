// // dp O(nm^2) time  O(mn) space
// public class Solution {
//     /**
//      * @param nums: an integer array and all positive numbers, no duplicates
//      * @param target: An integer
//      * @return: An integer
//      */
//     public int backPackIV(int[] A, int m) {
//         if (A == null || A.length == 0 || m <= 0) {
//             return 0;
//         }
        
//         int n = A.length;
        
//         int[][] dp = new int[n + 1][m + 1];  //dp[i][j] represents the number of ways of first i kinds of items to fill the j size of backpack
//         dp[0][0] = 1;
//         for (int i = 1; i <= m; i++) {
//             dp[0][i] = 0;
//         }
        
//         for (int i = 1; i <= n; i++) {
//             for (int j = 0; j <= m; j++) {
//                 for (int k = 0; k * A[i - 1] <= j; k++) {
//                     dp[i][j] += dp[i - 1][j - k * A[i - 1]];
//                 }
//             }
//         }
        
//         return dp[n][m];
//     }
// }



// // dp O(nm^2) time  O(m) space
// public class Solution {
//     /**
//      * @param nums: an integer array and all positive numbers, no duplicates
//      * @param target: An integer
//      * @return: An integer
//      */
//     public int backPackIV(int[] A, int m) {
//         if (A == null || A.length == 0 || m <= 0) {
//             return 0;
//         }
        
//         int n = A.length;
        
//         int[][] dp = new int[2][m + 1];  //dp[i][j] represents the number of ways of first i kinds of items to fill the j size of backpack
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
//                 dp[now][j] = 0;
//                 for (int k = 0; k * A[i - 1] <= j; k++) {
//                     dp[now][j] += dp[old][j - k * A[i - 1]];
//                 }
//             }
//         }
        
//         return dp[now][m];
//     }
// }


// dp O(nm) time  O(m) space
public class Solution {
    /**
     * @param nums: an integer array and all positive numbers, no duplicates
     * @param target: An integer
     * @return: An integer
     */
    public int backPackIV(int[] A, int m) {
        if (A == null || A.length == 0 || m <= 0) {
            return 0;
        }
        
        int n = A.length;
        
        int[][] dp = new int[2][m + 1];  //dp[i][j] represents the number of ways of first i kinds of items to fill the j size of backpack
        dp[0][0] = 1;
        for (int i = 1; i <= m; i++) {
            dp[0][i] = 0;
        }
        
        int old = 1;
        int now = 0;
        for (int i = 1; i <= n; i++) {
            old = now;
            now = 1 - now;
            for (int j = 0; j <= m; j++) {
                if (j < A[i - 1]) {
                    dp[now][j] = dp[old][j];
                } else {
                    dp[now][j] = dp[old][j] + dp[now][j - A[i - 1]];
                }
            }
        }
        
        return dp[now][m];
    }
}


// // dp O(nm) time  O(m) space  1D array
// public class Solution {
//     /**
//      * @param nums: an integer array and all positive numbers, no duplicates
//      * @param target: An integer
//      * @return: An integer
//      */
//     public int backPackIV(int[] A, int m) {
//         if (A == null || A.length == 0 || m <= 0) {
//             return 0;
//         }
        
//         int n = A.length;
        
//         int[] dp = new int[m + 1];  //dp[i][j] represents the number of ways of first i kinds of items to fill the j size of backpack
//         dp[0] = 1;
//         for (int i = 1; i <= m; i++) {
//             dp[i] = 0;
//         }
        
//         for (int i = 1; i <= n; i++) {
//             for (int j = A[i - 1]; j <= m; j++) {
//                 dp[j] += dp[j - A[i - 1]];
//             }
//         }
        
//         return dp[m];
//     }
// }