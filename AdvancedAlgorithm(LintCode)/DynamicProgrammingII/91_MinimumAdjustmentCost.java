// // dp
// public class Solution {
//     /*
//      * @param A: An integer array
//      * @param target: An integer
//      * @return: An integer
//      */
//     public int MinAdjustmentCost(List<Integer> A, int target) {
//         if (A == null || A.size() == 0) {
//             return 0;
//         }
        
//         int n = A.size();
//         int[][] dp = new int[n + 1][101];  // dp[i][j] represents the minimum cost for A[i] adjusts to j
        
//         // initialization
//         for (int i = 0; i <= n; i++) {
//             for (int j = 0; j <= 100; j++) {
//                 dp[i][j] = (i == 0 ? 0 : Integer.MAX_VALUE);
//             }
//         }
        
//         for (int i = 1; i <= n; i++) {
//             for (int j = 1; j <= 100; j++) {  // enumerates all the possible number each position can take
//                 for (int k = Math.max(1, j - target); k <= Math.min(100, j + target); k++) {  // the max difference constrain
//                     dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.abs(j - A.get(i - 1)));
//                 }
                
//                 // for (int k = 1; k <= 100; k++) {
//                 //     if (Math.abs(k - j) <= target) {  // the max difference constrain
//                 //         dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.abs(j - A.get(i - 1)));
//                 //     }
//                 // }
//             }
//         }
        
//         int res = Integer.MAX_VALUE;
//         for (int i = 1; i <= 100; i++) {
//             res = Math.min(res, dp[n][i]);
//         }
        
//         return res;
//     }
// }



// dp   rolling array
public class Solution {
    /*
     * @param A: An integer array
     * @param target: An integer
     * @return: An integer
     */
    public int MinAdjustmentCost(List<Integer> A, int target) {
        if (A == null || A.size() == 0) {
            return 0;
        }
        
        int n = A.size();
        int[][] dp = new int[2][101];  // dp[i][j] represents the minimum cost for A[i] adjusts to j
        
        // initialization
        for (int i = 0; i <= 100; i++) {
            dp[0][i] = 0;
            dp[1][i] = Integer.MAX_VALUE;
        }
        
        int old = 1;
        int now = 0;
        for (int i = 1; i <= n; i++) {
            old = now;
            now = 1 - now;
            for (int j = 1; j <= 100; j++) {  // enumerates all the possible number each position can take
                dp[now][j] = Integer.MAX_VALUE;
                for (int k = Math.max(1, j - target); k <= Math.min(100, j + target); k++) {  // the max difference constrain
                    dp[now][j] = Math.min(dp[now][j], dp[old][k] + Math.abs(j - A.get(i - 1)));
                }
                
                // for (int k = 1; k <= 100; k++) {
                //     if (Math.abs(k - j) <= target) {  // the max difference constrain
                //         dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.abs(j - A.get(i - 1)));
                //     }
                // }
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= 100; i++) {
            res = Math.min(res, dp[now][i]);
        }
        
        return res;
    }
}