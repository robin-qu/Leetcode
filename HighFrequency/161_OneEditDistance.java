// // General dp O(mn)time O(mn)space
// class Solution {
//     public boolean isOneEditDistance(String s, String t) {
//         if (s == null || t == null) {
//             return false;
//         }
        
//         int m = s.length();
//         int n = t.length();
//         char[] ss = s.toCharArray();
//         char[] tt = t.toCharArray();
        
//         int[][] dp = new int[m + 1][n + 1];
//         dp[0][0] = 0;
//         for (int i = 1; i <= m; i++) {
//             dp[i][0] = i;
//         }
//         for (int i = 1; i <= n; i++) {
//             dp[0][i] = i;
//         }
        
//         for (int i = 1; i <= m; i++) {
//             for (int j = 1; j <= n; j++) {
//                 if (ss[i - 1] == tt[j - 1]) {
//                     dp[i][j] = dp[i - 1][j - 1];
//                     continue;
//                 }
                
//                 // insert, delete, replace
//                 dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
//             }
//         }
        
//         return dp[m][n] == 1;
//     }
// }

// General dp O(m)time O(1)space
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        
        int m = s.length();
        int n = t.length();
        
        for (int i = 0; i < Math.min(m, n); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                // insert
                boolean p1 = s.substring(i, m).equals(t.substring(i + 1, n));
                // delete
                boolean p2 = s.substring(i + 1, m).equals(t.substring(i, n));
                // replace
                boolean p3 =  s.substring(i + 1, m).equals(t.substring(i + 1, n));
                return p1 || p2 || p3;
            }
        }
        
        return Math.abs(m - n) == 1;
    }
}