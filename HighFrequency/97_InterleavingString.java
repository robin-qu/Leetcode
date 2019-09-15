// // dp O(mn)time O(mn)space
// class Solution {
//     public boolean isInterleave(String s1, String s2, String s3) {
//         if (s1 == null || s2 == null || s3 == null) {
//             return false;
//         }
        
//         char[] ss1 = s1.toCharArray();
//         char[] ss2 = s2.toCharArray();
//         char[] ss3 = s3.toCharArray();
//         int n1 = s1.length();
//         int n2 = s2.length();
//         int n = s3.length();
        
//         if (n != n1 + n2) {
//             return false;
//         }
        
//         boolean[][] dp = new boolean[n1 + 1][n2 + 1]; // dp[i][j] represent whether the first (i + j) characters in s3 interleaving of the first i characters in s1 and the first j characters in s2 is
        
//         dp[0][0] = true;
//         for (int i = 1; i <= n1; i++) {
//             if (dp[i - 1][0] && ss1[i - 1] == ss3[i - 1]) {
//                 dp[i][0] = true;
//             }
//         }
        
//         for (int i = 1; i <= n2; i++) {
//             if (dp[0][i - 1] && ss2[i - 1] == ss3[i - 1]) {
//                 dp[0][i] = true;
//             }
//         }
        
//         for (int i = 1; i <= n1; i++) {
//             for (int j = 1; j <= n2; j++) {
//                 if (ss2[j - 1] == ss3[i + j - 1]) {
//                     dp[i][j] = dp[i][j] || dp[i][j - 1];
//                 }
                
//                 if (ss1[i - 1] == ss3[i + j - 1]) {
//                     dp[i][j] = dp[i][j] || dp[i - 1][j];
//                 }
//             }
//         }
        
//         return dp[n1][n2];
//     }
// }


// dp O(mn)time O(n)space
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        
        char[] ss1 = s1.toCharArray();
        char[] ss2 = s2.toCharArray();
        char[] ss3 = s3.toCharArray();
        int n1 = s1.length();
        int n2 = s2.length();
        int n = s3.length();
        
        if (n != n1 + n2) {
            return false;
        }
        
        boolean[] dp = new boolean[n2 + 1]; // dp[i][j] represent whether the first (i + j) characters in s3 interleaving of the first i characters in s1 and the first j characters in s2 is
        
        dp[0] = true;        
        for (int i = 1; i <= n2; i++) {
            if (dp[i - 1] && ss2[i - 1] == ss3[i - 1]) {
                dp[i] = true;
            }
        }
        
        for (int i = 1; i <= n1; i++) {
            for (int j = 0; j <= n2; j++) {
                if (j == 0) {
                    dp[j] = ss3[i - 1] == ss1[i - 1] && dp[j];
                    continue;
                }
                
                dp[j] = ss1[i - 1] == ss3[i + j - 1] && dp[j];
                
                dp[j] = dp[j] || (ss2[j - 1] == ss3[i + j - 1] && dp[j - 1]);
            }
        }
        
        return dp[n2];
    }
}