public class Solution {
    /**
     * @param A: An integer array
     * @return: An integer
     */
    public int stoneGame(int[] A) {
        if (A == null || A.length <= 1) {
            return 0;
        }
        
        int n = A.length;
        if (n == 1) {
            return A[0];
        }
        
        int[][] dp = new int[n][n];  // dp[i][j] represents the sum (score) from number i to number j
        int[] prefixSum = new int[n + 1];  // the prefix sum of the first i numbers
        
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + A[i];
        }
        
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int left = i; // start position
                int right = i + len - 1; // end position
                int min = Integer.MAX_VALUE;
                for (int mid = left; mid < right; mid++) { // partition point
                    min = Math.min(min, dp[left][mid] + dp[mid + 1][right]);
                }
                
                dp[left][right] = min + prefixSum[right + 1] - prefixSum[left];
            }
        }
        
        return dp[0][n - 1];
    }
}