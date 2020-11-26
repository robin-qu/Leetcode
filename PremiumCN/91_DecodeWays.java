// class Solution {
//     public int numDecodings(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }

//         if (s.charAt(0) == '0') {
//             return 0;
//         }

//         int n = s.length();
//         char[] ss = s.toCharArray();

//         int[] dp = new int[n + 1];
//         dp[0] = 1;
//         dp[1] = 1;
//         for (int i = 1; i < n; i++) {
//             if (ss[i] == '0') {
//                 if (ss[i - 1] == '1' || ss[i - 1] == '2') {
//                     dp[i + 1] = dp[i - 1];
//                 } else {
//                     return 0;
//                 }
//             } else if (ss[i] >= '1' && ss[i] <= '6') {
//                 if (ss[i - 1] == '1' || ss[i - 1] == '2') {
//                     dp[i + 1] = dp[i] + dp[i - 1];
//                 } else {
//                     dp[i + 1] = dp[i];
//                 }
//             } else {
//                 if (ss[i - 1] == '1') {
//                     dp[i + 1] = dp[i] + dp[i - 1];
//                 } else {
//                     dp[i + 1] = dp[i];
//                 }
//             }
//         }

//         return dp[n];
//     }
// }



class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        if (s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        char[] ss = s.toCharArray();

        int prev = 1;
        int prevprev = 1;
        int curr = 1;
        for (int i = 1; i < n; i++) {
            if (ss[i] == '0') {
                if (ss[i - 1] == '1' || ss[i - 1] == '2') {
                    curr = prevprev;
                } else {
                    return 0;
                }
            } else if (ss[i] >= '1' && ss[i] <= '6') {
                if (ss[i - 1] == '1' || ss[i - 1] == '2') {
                    curr = prev + prevprev;
                } else {
                    curr = prev;
                }
            } else {
                if (ss[i - 1] == '1') {
                    curr = prev + prevprev;
                } else {
                    curr = prev;
                }
            }
            prevprev = prev;
            prev = curr;
        }

        return curr;
    }
}