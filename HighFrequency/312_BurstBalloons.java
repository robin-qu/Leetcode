// DP O(n^3)time O(n^2)space
class Solution {
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] A = new int[n + 2];
        A[0] = 1;
        A[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            A[i + 1] = nums[i];
        }
        int[][] dp = new int[n + 2][n + 2];  // dp[i][j] represents the maximum number of coins one can get from (i + 1)-th balloon to (j - 1)-th ballon
        
        for (int len = 3; len <= n + 2; len++) {
            for (int i = 0; i + len - 1 < n + 2; i++) {
                int j = i + len - 1;
                for (int k = i + 1; k <= j - 1; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + A[i] * A[k] * A[j] + dp[k][j]);
                }
            }
        }
        
        return dp[0][n + 1];
    }
}