class Solution {
    /**
     * @param A: A list of integers
     * @return: A boolean
     */
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }
        
        int n = A.length;
        int[] dp = new int[n];
        dp[0] = 1;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] > 0 && A[j] >= i - j) {
                    dp[i]++;
                }
            }
        }
        
        return dp[n - 1] > 0;
    }
}