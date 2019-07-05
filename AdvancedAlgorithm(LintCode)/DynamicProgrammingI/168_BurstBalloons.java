public class Solution {
    /**
     * @param nums: A list of integer
     * @return: An integer, maximum coins
     */
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
        int[][] dp = new int[n + 2][n + 2];
        
        
        for (int len = 2; len < n + 2; len++) {  // length of the interval
            for (int i = 0; i + len < n + 2; i++) {  // i is start position, i + len is end position
                int start = i;
                int end = i + len;
                int max = Integer.MIN_VALUE;
                for (int mid = i + 1; mid < i + len; mid++) {  // last balloon to be broken between the interval
                    max = Math.max(max, dp[start][mid] + dp[mid][end] + A[start] * A[mid] * A[end]);
                }
                dp[start][end] = max;
            }
        }
        
        return dp[0][n + 1];
    }
}