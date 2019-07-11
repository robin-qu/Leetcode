// // DP O(mn) space
// public class Solution {
//     /**
//      * @param m: An integer m denotes the size of a backpack
//      * @param A: Given n items with size A[i]
//      * @param V: Given n items with value V[i]
//      * @return: The maximum value
//      */
//     public int backPackII(int m, int[] A, int[] V) {
//         if (A == null || A.length == 0 ||
//             V == null || V.length == 0 || m <= 0) {
//             return 0;
//         }
        
//         int n = A.length;
        
//         int[][] dp = new int[n + 1][m + 1];  // dp[i][j] represents the maximum value of the first n items that can fill size j of the backpack, -1 if cannot fill
        
//         // initialization
//         dp[0][0] = 0;  // the first 0 item can fill size 0, with value 0
//         for (int i = 1; i <= n; i++) {
//             dp[i][0] = -1;  // the first i items cannot fill size 0
//         }
//         for (int i = 0; i <= n; i++) {
//             for (int j = 1; j <= m; j++) {
//                 dp[i][j] = -1;
//             }
//         }
        
//         int max = 0;
//         for (int i = 1; i <= n; i++) {
//             for (int j = 1; j <= m; j++) {
//                 if (j < A[i - 1]) { // cannot put curretn item into the backpack
//                     dp[i][j] = dp[i - 1][j];
//                 } else {
//                     if (dp[i - 1][j - A[i - 1]] > -1) {
//                         dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - A[i - 1]] + V[i - 1]);
//                     } else {  // dp[i - 1][j - A[i - 1]] == -1
//                         dp[i][j] = Math.max(dp[i - 1][j], V[i - 1]);
//                     }
//                 }
                
//                 max = Math.max(max, dp[i][j]);
//             }
//         }
        
//         return max;
//     }
// }



// DP O(m) space
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        if (A == null || A.length == 0 ||
            V == null || V.length == 0 || m <= 0) {
            return 0;
        }
        
        int n = A.length;
        
        int[][] dp = new int[2][m + 1];  // dp[i][j] represents the maximum value of the first n items that can fill size j of the backpack, -1 if cannot fill
        
        // initialization
        dp[0][0] = 0;  // the first 0 item can fill size 0, with value 0
        dp[1][0] = -1;  // the first i items cannot fill size 0
        for (int i = 1; i <= m; i++) {
            dp[0][i] = -1;
            dp[1][i] = -1;
        }
        
        int max = 0;
        int old = 1;
        int now = 0;
        for (int i = 1; i <= n; i++) {
            now = old;
            old = 1 - now;
            for (int j = 1; j <= m; j++) {
                if (j < A[i - 1]) { // cannot put curretn item into the backpack
                    dp[now][j] = dp[old][j];
                } else {
                    if (dp[old][j - A[i - 1]] > -1) {
                        dp[now][j] = Math.max(dp[old][j], dp[old][j - A[i - 1]] + V[i - 1]);
                    } else {  // dp[i - 1][j - A[i - 1]] == -1
                        dp[now][j] = Math.max(dp[old][j], V[i - 1]);
                    }
                }
                
                max = Math.max(max, dp[now][j]);
            }
        }
        
        return max;
    }
}