public class Solution {
    /**
     * @param s1: A string
     * @param s2: A string
     * @param s3: A string
     * @return: Determine whether s3 is formed by interleaving of s1 and s2
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        
        int n1 = s1.length();
        int n2 = s2.length();
        int m = s3.length();
        if (n1 + n2 != m) {
            return false;
        }
        char[] ss1 = s1.toCharArray();
        char[] ss2 = s2.toCharArray();
        char[] ss3 = s3.toCharArray();
        
        boolean[][] dp = new boolean[n1 + 1][n2 + 1];  // dp[i][j] represents whether the interleaving of the first i characters of s1 and the first j characters of s2 is the first i + j - 1 characters of s3
        
        // initialization
        dp[0][0] = true;
        for (int i = 1; i <= n1; i++) {
            dp[i][0] = dp[i - 1][0] && ss1[i - 1] == ss3[i - 1];
            if (!dp[i][0]) {
                break;
            }
        }
        for (int i = 1; i <= n2; i++) {
            dp[0][i] = dp[0][i - 1] && ss2[i - 1] == ss3[i - 1];
            if (!dp[0][i]) {
                break;
            }
        }
        
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                dp[i][j] = (dp[i - 1][j] && ss1[i - 1] == ss3[i + j- 1]) || (dp[i][j - 1] && ss2[j - 1] == ss3[i + j - 1]);
            }
        }
        
        return dp[n1][n2];
    }
}