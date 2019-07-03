// // Initial ugly version
// public class Solution {
//     /**
//      * @param s: a string,  encoded message
//      * @return: an integer, the number of ways decoding
//      */
//     public int numDecodings(String s) {
//         if (s == null || s.length() == 0 || s.charAt(0) == '0') {
//             return 0;
//         }
        
//         if (s.length() == 1) {
//             return 1;
//         }
        
//         char[] ss = s.toCharArray();
//         int n = s.length();
//         int[] dp = new int[n];
//         dp[0] = 1;
//         if (ss[1] == '0') {
//             if (ss[0] <= '2') {
//                 dp[1] = 1;
//             } else {
//                 return 0;
//             }
//         } else if ((ss[0] - '0') * 10 + (ss[1] - '0') <= 26) {
//             dp[1] = 2;
//         } else {
//             dp[1] = 1;
//         }
        
//         for (int i = 2; i < n; i++) {
//             if (ss[i] == '0') {
//                 if (ss[i - 1] > '2' || ss[i - 1] == '0') {
//                     return 0;
//                 } else {
//                     dp[i] = dp[i - 2];
//                 }
//             } else if (ss[i - 1] != '0' && (ss[i - 1] - '0') * 10 + (ss[i] - '0') <= 26) {
//                 dp[i] = dp[i - 1] + dp[i - 2];
//             } else {
//                 dp[i] = dp[i - 1];
//             }
//         }
        
//         return dp[n - 1];
//     }
// }


// Imporved clean version
public class Solution {
    /**
     * @param s: a string,  encoded message
     * @return: an integer, the number of ways decoding
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        
        int n = s.length();
        char[] ss = s.toCharArray();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = ss[0] == '0' ? 0 : 1;
        
        for (int i = 2; i <= n; i++) {
            if (ss[i - 1] != '0') {
                dp[i] = dp[i - 1];
            }
            
            int num = (ss[i - 2] - '0') * 10 + (ss[i - 1] - '0');
            
            if (num >= 10 && num <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        
        return dp[n];
    }
}