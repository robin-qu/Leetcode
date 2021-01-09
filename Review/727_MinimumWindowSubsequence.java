class Solution {
    public String minWindow(String S, String T) {
        if (S == null || T == null || S.length() == 0 || T.length() == 0) {
            return "";
        }

        int m = T.length();
        int n = S.length();
        char[] ss = S.toCharArray();
        char[] tt = T.toCharArray();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            if (ss[i] == tt[0]) {
                dp[1][i + 1] = i;
            } else {
                dp[1][i + 1] = dp[1][i];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (tt[i] == ss[j]) {
                    if (dp[i][j] != -1) {
                        dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j]);
                    }
                } else {
                    dp[i + 1][j + 1] = dp[i + 1][j];
                }
            }
        }

        String res = "";
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (dp[m][i + 1] != -1 && i - dp[m][i + 1] + 1 < min) {
                res = S.substring(dp[m][i + 1], i + 1);
                min = i - dp[m][i + 1] + 1;
            }
        }
        return res;
    }
}