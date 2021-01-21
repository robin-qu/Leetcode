class Solution {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }

        int n1 = word1.length();
        int n2 = word2.length();
        char[] ss1 = word1.toCharArray();
        char[] ss2 = word2.toCharArray();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 0; i < n1; i++) {
            dp[i + 1][0] = i + 1;
        }
        for (int i = 0; i < n2; i++) {
            dp[0][i + 1] = i + 1;
        }

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                // insert
                dp[i + 1][j + 1] = dp[i + 1][j] + 1;
                // delete
                dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j + 1] + 1);
                // replace
                if (ss1[i] == ss2[j]) {
                    dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j]);
                } else {
                    dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j] + 1);
                }
            }
        }

        return dp[n1][n2];
    }
}