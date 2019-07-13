// // dp O(n*k*target)time  O(n*k*target)space
// public class Solution {
//     /**
//      * @param A: An integer array
//      * @param k: A positive integer (k <= length(A))
//      * @param target: An integer
//      * @return: An integer
//      */
//     public int kSum(int[] A, int k, int target) {
//         if (A == null || A.length == 0 || target == 0) {
//             return 0;
//         }
        
//         int n = A.length;
        
//         int[][][] dp = new int[n + 1][k + 1][target + 1];  // dp[i][j][s]  represents the number of solutions that the sum of p of the first i numbers is s
        
//         dp[0][0][0] = 1;
        
//         for (int i = 1; i <= n; i++) {
//             for (int j = 0; j <= k; j++) {
//                 for (int m = 0; m <= target; m++) {
//                     dp[i][j][m] = dp[i - 1][j][m];  // current number can not be added into the sum
                    
//                     if (j >= 1 && A[i - 1] <= m) {
//                         dp[i][j][m] += dp[i - 1][j - 1][m - A[i - 1]];  // current number is added into the sum
//                     }
//                 }
//             }
//         }
        
//         return dp[n][k][target];
//     }
// }


// // dp O(n*k*target)time O(k*target) space rolling array
// public class Solution {
//     /**
//      * @param A: An integer array
//      * @param k: A positive integer (k <= length(A))
//      * @param target: An integer
//      * @return: An integer
//      */
//     public int kSum(int[] A, int k, int target) {
//         if (A == null || A.length == 0 || target == 0) {
//             return 0;
//         }
        
//         int n = A.length;
        
//         int[][][] dp = new int[2][k + 1][target + 1];  // dp[i][j][s]  represents the number of solutions that the sum of p of the first i numbers is s
        
//         dp[0][0][0] = 1;
        
//         int old = 1;
//         int now = 0;
//         for (int i = 1; i <= n; i++) {
//             old = now;
//             now = 1 - now;
//             for (int j = 0; j <= k; j++) {
//                 for (int m = 0; m <= target; m++) {
//                     dp[now][j][m] = dp[old][j][m];  // current number can not be added into the sum
                    
//                     if (j >= 1 && A[i - 1] <= m) {
//                         dp[now][j][m] += dp[old][j - 1][m - A[i - 1]];  // current number is added into the sum
//                     }
//                 }
//             }
//         }
        
//         return dp[now][k][target];
//     }
// }


// dp O(n*k*target)time O(k*target) space 2D array
public class Solution {
    /**
     * @param A: An integer array
     * @param k: A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */
    public int kSum(int[] A, int k, int target) {
        if (A == null || A.length == 0 || target == 0) {
            return 0;
        }
        
        int n = A.length;
        
        int[][] dp = new int[k + 1][target + 1];  // dp[i][j][s]  represents the number of solutions that the sum of p of the first i numbers is s
        
        dp[0][0] = 1;
        
        for (int i = 1; i <= n; i++) {
            for (int j = k; j >= 0; j--) {
                for (int m = target; m >= A[i - 1]; m--) {
                    if (j >= 1) {
                        dp[j][m] += dp[j - 1][m - A[i - 1]];  // current number is added into the sum
                    }
                }
            }
        }
        
        return dp[k][target];
    }
}