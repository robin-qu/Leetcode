class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int m = s.length();
        int n = p.length();
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            if (pp[i] == '*') {
                dp[0][i + 1] = dp[0][i];
            } else {
                break;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (pp[j - 1] == '?' || ss[i - 1] == pp[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pp[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }
}