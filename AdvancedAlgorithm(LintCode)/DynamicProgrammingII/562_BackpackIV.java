// dp O(nm^2) time  O(mn) space
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
        
        int[][] dp = new int[n + 1][m + 1];  //dp[i][j] represents the number of ways of first i kinds of items to fill the j size of backpack
        dp[0][0] = 1;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (A[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    for (int k = 0; k * A[i - 1] <= j; k++) {
                        if (dp[i - 1][j - k * A[i - 1]] != 0) {
                            dp[i][j] += dp[i - 1][j - k * A[i - 1]];
                        }
                    }
                }
            }
        }
        
        return dp[n][m];
    }
}