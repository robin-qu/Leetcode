class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        
        char[] ss = s.toCharArray();
        int n = s.length();
        int[] dp = new int[n + 1];  // dp[i] represents the number of decode ways of the first i digits in s
        dp[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            // single digit
            if (ss[i - 1] != '0') {   //  "10"
                dp[i] += dp[i - 1];
            }
            
            // double digits
            if (i > 1) {
                if (ss[i - 1] == '0' && (ss[i - 2] == '0' || ss[i - 2] > '2')) {  // "801"
                    return 0;
                }
                
                int twoDigits = (ss[i - 2] - '0') * 10 + ss[i - 1] - '0';
                if (twoDigits >= 10 && twoDigits <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        
        return dp[n];
    }
}