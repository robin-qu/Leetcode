class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return false;
        }

        int n = s.length();

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        Set<String> words = new HashSet(wordDict);
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (words.contains(s.substring(j, i))) {
                    dp[i] = dp[i] || dp[j];
                }
            }
        }

        return dp[n];
    }
}