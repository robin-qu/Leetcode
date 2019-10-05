// O(10N)time O(10N)space
class Solution {
    private int[][] nexts = new int[][]{{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};
    private static final int MOD = (int) (1e9) + 7;
    
    public int knightDialer(int N) {
        int[][] dp = new int[N][10];  // dp[i][j] represents the number of distinct numbers at key j after N - 1 hops
        
        for (int i = 0; i < 10; i++) {
            dp[0][i] = 1;
        }
        
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int next : nexts[j]) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][next]) % MOD;
                }
            }
        }
        
        int res = 0;
        for (int count : dp[N - 1]) {
            res = (res + count) % MOD;
        }
        
        return res;
    }
}