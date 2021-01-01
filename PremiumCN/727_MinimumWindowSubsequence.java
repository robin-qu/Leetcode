class Solution {
    public String minWindow(String S, String T) {
        if (S == null || T == null || T.length() == 0) {
            return "";
        }

        int m = S.length();
        int n = T.length();
        char[] ss = S.toCharArray();
        char[] tt = T.toCharArray();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -1;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (ss[j - 1] == tt[i - 1]) {
                    if (i - 1 == 0) {
                        dp[i][j] = j - 1;
                    } else if (dp[i - 1][j - 1] != -1) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        int[] indices = new int[2];
        Arrays.fill(indices, -1);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= m; i++) {
            if (dp[n][i] != -1 && i - dp[n][i] < min) {
                min = i - dp[n][i];
                indices[0] = dp[n][i];
                indices[1] = i;
            }
        }

        if (indices[0] == -1) {
            return "";
        }

        return S.substring(indices[0], indices[1]);
    }
} 