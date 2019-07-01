class Solution {
    /**
     * @param m: positive integer (1 <= m <= 100)
     * @param n: positive integer (1 <= n <= 100)
     * @return: An integer
     */
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j != 0) {
                    dp[j] = dp[j - 1] + dp[j];
                }
            }
        }
        
        return dp[n - 1];
    }
}