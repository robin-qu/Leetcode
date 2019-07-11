// // O(nm) space dp
// public class Solution {
//     /**
//      * @param m: An integer m denotes the size of a backpack
//      * @param A: Given n items with size A[i]
//      * @return: The maximum size
//      */
//     public int backPack(int m, int[] A) {
//         if (A == null || A.length == 0 || m <= 0) {
//             return 0;
//         }
        
//         int n = A.length;
//         boolean[][] dp = new boolean[n + 1][m + 1];  // dp[i][j] represents whether the some of the first i items can fill size j of the backpack (add up to j)
        
//         // initialization
//         dp[0][0] = true;  // 0 items can fill size 0
//         for (int i = 1; i <= m; i++) {
//             dp[0][i] = false;  // 0 items cannot fill size i
//         }
        
//         for (int i = 1; i <= n; i++) {
//             for (int j = 0; j <= m; j++) {
//                 if (j < A[i - 1]) {
//                     dp[i][j] = dp[i - 1][j];
//                 } else {
//                     dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A[i - 1]];
//                 }
//             }
//         }
        
//         for (int i = m; i >= 0; i--) {
//             if (dp[n][i]) {
//                 return i;
//             }
//         }
        
//         return 0;
//     }
// }


// O(m) space dp
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        if (A == null || A.length == 0 || m <= 0) {
            return 0;
        }
        
        int n = A.length;
        boolean[][] dp = new boolean[2][m + 1];  // dp[i][j] represents whether some of the first i items can fill size j of the backpack (add up to j)
        
        // initialization
        dp[0][0] = true;  // 0 items can fill size 0
        for (int i = 1; i <= m; i++) {
            dp[0][i] = false;  // 0 items cannot fill size i
        }
        int old = 1;
        int now = 0;
        
        for (int i = 1; i <= n; i++) {
            now = old;
            old = 1 - now;
            for (int j = 0; j <= m; j++) {
                if (j < A[i - 1]) {
                    dp[now][j] = dp[old][j];  // cannot put the current item into backpack
                } else {
                    dp[now][j] = dp[old][j] || dp[old][j - A[i - 1]];
                    // either put into the backpack or not
                }
            }
        }
        
        for (int i = m; i >= 0; i--) {
            if (dp[now][i]) {
                return i;
            }
        }
        
        return 0;
    }
}