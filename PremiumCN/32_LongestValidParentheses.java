// class Solution {
//     public int longestValidParentheses(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }

//         int n = s.length();
//         char[] ss = s.toCharArray();
//         int res = 0;
//         int[] dp = new int[n + 1];
//         for (int i = 0; i < n; i++) {
//             if (ss[i] == '(') {
//                 dp[i + 1] = 0;
//             } else {
//                 if (i - dp[i] - 1 >= 0 && ss[i - dp[i] - 1] == '(') {
//                     dp[i + 1] = 2 + dp[i] + dp[i - dp[i] - 1];
//                 } else {
//                     dp[i + 1] = 0;
//                 }
//             }
//             res = Math.max(res, dp[i + 1]);
//         }
//         return res;
//     }
// }


// class Solution {
//     public int longestValidParentheses(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }

//         int n = s.length();
//         char[] ss = s.toCharArray();
//         int res = 0;
//         Deque<Integer> stack = new ArrayDeque<>();
//         stack.push(-1);
//         for (int i = 0; i < n; i++) {
//             if (ss[i] == '(') {
//                 stack.push(i);
//             } else {
//                 stack.pop();
//                 if (stack.isEmpty()) {
//                     stack.push(i);
//                 } else {
//                     res = Math.max(res, i - stack.peek());
//                 }
//             }
//         }
//         return res;
//     }
// }


class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        char[] ss = s.toCharArray();
        int res = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < n; i++) {
            if (ss[i] == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                res = Math.max(res, 2 * right);
            } else if (right > left) {
                left = 0;
                right = 0;
            }
        }
        left = 0;
        right = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (ss[i] == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                res = Math.max(res, 2 * left);
            } else if (left > right) {
                left = 0;
                right = 0;
            }
        }
        return res;
    }
}