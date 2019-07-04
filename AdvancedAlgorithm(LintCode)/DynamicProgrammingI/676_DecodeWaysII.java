// // O(n) space DP
// public class Solution {
//     /**
//      * @param s: a message being encoded
//      * @return: an integer
//      */
//     public int numDecodings(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }
        
//         int n = s.length();
//         final long MOD = (long) 1e9 + 7;
//         char[] ss = s.toCharArray();
//         long[] dp = new long[n + 1];
//         dp[0] = 1;
//         if (ss[0] == '0') {
//             dp[1] = 0;
//         } else if (ss[0] =='*') {
//             dp[1] = 9;
//         } else {
//             dp[1] = 1;
//         }
        
//         for (int i = 1; i < n; i++) {
//             char curr = ss[i];
//             if (curr == '*') {
//                 dp[i + 1] = dp[i] * 9;
//             } else if (curr != '0') {  // '1' ~ '9'
//                 dp[i + 1] = dp[i];
//             }
            
//             char prev = ss[i - 1];
//             if (prev == '*' && curr == '*') {  // "**"
//                 dp[i + 1] += 15 * dp[i - 1];
//             } else if (prev == '1' && curr == '*') {  // "1*"
//                 dp[i + 1] += 9 * dp[i - 1];
//             } else if (prev == '2' && curr == '*') {  // "2*"
//                 dp[i + 1] += 6 * dp[i - 1];
//             } else if (prev == '*' && curr >= '0' && curr <= '6') {
//                 dp[i + 1] += 2 * dp[i - 1];
//             } else if (prev == '*' && curr >= '7') {
//                 dp[i + 1] += dp[i - 1];
//             } else if (prev != '*' && curr != '*') {
//                 int num = 10 * (prev - '0') + (curr - '0');
//                 if (num >= 10 && num <= 26) {
//                     dp[i + 1] += dp[i - 1];
//                 }
//             }
            
//             dp[i + 1] = dp[i + 1] % MOD;
//         }
        
//         return (int) dp[n];
//     }
// }


// O(1) space DP
public class Solution {
    /**
     * @param s: a message being encoded
     * @return: an integer
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        final long MOD = (long) 1e9 + 7;
        char[] ss = s.toCharArray();
        long first = 0;
        long second = 1;
        first = 1;
        if (ss[0] == '0') {
            second = 0;
        } else if (ss[0] =='*') {
            second = 9;
        } else {
            second = 1;
        }
        
        for (int i = 1; i < n; i++) {
            char curr = ss[i];
            long temp = 0;
            if (curr == '*') {
                temp = second * 9;
            } else if (curr != '0') {  // '1' ~ '9'
                temp = second;
            }
            
            char prev = ss[i - 1];
            if (prev == '*' && curr == '*') {  // "**"
                temp += 15 * first;
            } else if (prev == '1' && curr == '*') {  // "1*"
                temp += 9 * first;
            } else if (prev == '2' && curr == '*') {  // "2*"
                temp += 6 * first;
            } else if (prev == '*' && curr >= '0' && curr <= '6') {
                temp += 2 * first;
            } else if (prev == '*' && curr >= '7') {
                temp += first;
            } else if (prev != '*' && curr != '*') {
                int num = 10 * (prev - '0') + (curr - '0');
                if (num >= 10 && num <= 26) {
                    temp += first;
                }
            }
            
            first = second;
            second = temp % MOD;
        }
        
        return (int) second;
    }
}