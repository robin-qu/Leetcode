// // DP  O(n^2)time O(n^2)space
// class Solution {
//     public String longestPalindrome(String s) {
//         if (s == null || s.length() == 0) {
//             return "";
//         }
        
//         int n = s.length();
//         char[] ss = s.toCharArray();
        
//         boolean[][] dp = new boolean[n][n];  // dp[i][j] represents whether substring(i, j + 1) is palindromic
//         int max = 1;
//         String res = s.substring(0, 1);
        
//         for (int len = 1; len <= n; len++) {
//             for (int i = 0; i + len <= n; i++) {  // i start position
//                 int j = i + len - 1;  // j end position
//                 if (len == 1) {
//                     dp[i][j] = true;
//                 } else if (len == 2) {
//                     dp[i][j] = ss[i] == ss[j];
//                 } else {
//                     dp[i][j] = (dp[i + 1][j - 1] && ss[i] == ss[j]);
//                 }
                
//                 if (dp[i][j] && len > max) {
//                     res = s.substring(i, j + 1);
//                     max = len;
//                 }
//             }
//         }
        
//         return res;
//     }
// }



// two pointers
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        int n = s.length();
        char[] ss = s.toCharArray();
        String res = "";
        
        for (int i = 0; i < n; i++) {
            String s1 = expand(i, i, n, ss);  // odd length
            if (s1.length() > res.length()) {
                res = s1;
            }
            
            if (i > 0) {
                String s2 = expand(i - 1, i, n, ss);  // even length
                if (s2.length() > res.length()) {
                    res = s2;
                }
            }
        }
        
        return res;
    }
    
    private String expand(int i, int j, int n, char[] ss) {
        int left = i;
        int right = j;
        String res = "";
        
        while (left >= 0 && right < n && ss[left] == ss[right]) {
            if (left == right) {
                res = String.valueOf(ss[left]);
            } else {
                res = ss[left] + res + ss[right];
            }
            
            left--;
            right++;
        }
        
        return res;
    }
}