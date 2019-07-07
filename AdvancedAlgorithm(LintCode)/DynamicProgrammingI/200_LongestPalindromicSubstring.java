// // DP O(n^2)
// public class Solution {
//     /**
//      * @param s: input string
//      * @return: the longest palindromic substring
//      */
//     public String longestPalindrome(String s) {
//         if (s == null || s.length() == 0) {
//             return "";
//         }
        
//         int n = s.length();
//         char[] ss = s.toCharArray();
//         int[][] dp = new int[n][n];
//         int max = 0;
//         String maxS = "";
        
//         for (int len = 1; len <= n; len++) {
//             for (int i = 0; i + len - 1 < n; i++) {  // left position
//                 int j = i + len - 1;  // right position
//                 if (len == 1) {
//                     dp[i][j] = 1;
//                 } else if (len == 2) {
//                     if (ss[i] == ss[j]) {
//                         dp[i][j] = 2;
//                     }
//                 } else {
//                     if (dp[i + 1][j - 1] > 0 && ss[i] == ss[j]) {
//                         dp[i][j] = dp[i + 1][j - 1] + 2;
//                     }
//                 }
                
//                 if (dp[i][j] > max) {
//                     maxS = s.substring(i, j + 1);
//                     max = dp[i][j];
//                 }
//             }
//         }
        
//         return maxS;
//     }
// } 


// Two Pointers O(n^2)
public class Solution {
    /**
     * @param s: input string
     * @return: the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        int n = s.length();
        int max = 0;
        String maxS = "";
        
        for (int i = 0; i < n; i++) {
            if (getPalindrome(s, i, i).length() > max) {
                maxS = getPalindrome(s, i, i);
                max = getPalindrome(s, i, i).length();
            }
            
            if (i > 0 && getPalindrome(s, i - 1, i).length() > max) {
                maxS = getPalindrome(s, i - 1, i);
                max = getPalindrome(s, i - 1, i).length();
            }
        }

        return maxS;
    }
    
    private String getPalindrome(String s, int start, int end) {
        int left = start;
        int right = end;
        
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        
        return s.substring(left + 1, right);
    }
} 