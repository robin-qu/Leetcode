class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        int m = s.length();
        int n = p.length();
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        
        boolean[][] dp = new boolean[m + 1][n + 1];  // dp[i][j] represents whether the first i characters of s and the first j characters fo p match
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (pp[i - 1] == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (pp[j - 1] == '*') {
                    if (dp[i][j - 2]) {
                        dp[i][j] = true;  // '*' counts as empty, repeat 0 time
                        continue;
                    }
                    if (pp[j - 2] == ss[i - 1] || pp[j - 2] == '.') {
                        dp[i][j] = dp[i - 1][j];  // repeat as least 1 times
                    }
                } else if (pp[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && ss[i - 1] == pp[j - 1];
                }
            }
        }
        
        return dp[m][n];
    }
}