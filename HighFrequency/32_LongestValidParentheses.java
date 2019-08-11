// // DP O(n^3)time O(n^2)space
// class Solution {
//     public int longestValidParentheses(String s) {
//         if (s == null || s.length() < 2) {
//             return 0;
//         }
        
//         int n = s.length();
//         char[] ss = s.toCharArray();
//         boolean[][] dp = new boolean[n][n];  // dp[i][j] represents where substring(i, j) is a string of valid parentheses
        
//         // initialization
//         int max = 0;
//         for (int i = 0; i < n - 1; i++) {
//             if (ss[i] == '(' && ss[i + 1] == ')') {
//                 dp[i][i + 1] = true;
//                 max = 2;
//             }
//         }
        
//         for (int len = 4; len <= n; len += 2) {
//             for (int i = 0; i + len - 1 < n; i++) {
//                 int j = i + len - 1;
//                 if (ss[i] == '(' && dp[i + 1][j - 1] && ss[j] == ')') {
//                     dp[i][j] = true;   //   "(X...X)"
//                 }
                
//                 for (int mid = i + 1; mid <= j - 1; mid += 2) {
//                     if (dp[i][mid] && dp[mid + 1][j]) {
//                         dp[i][j] = true;
//                     }
//                 }
                
//                 if (dp[i][j]) {
//                     max = Math.max(max, len);
//                 }
//             }
//         }
        
//         return max;
//     }
// }


// // DP O(n)time O(n)space
// class Solution {
//     public int longestValidParentheses(String s) {
//         if (s == null || s.length() < 2) {
//             return 0;
//         }
        
//         int n = s.length();
//         char[] ss = s.toCharArray();
//         int[] dp = new int[n + 1];  // dp[i] represents the longest length of a valid parentheses expression ended with ss[i - 1]
        
//         int max = 0;
//         for (int i = 2; i <= n; i++) {
//             if (ss[i - 1] == ')' && ss[i - 2] == '(') {  // "X...X()"
//                 dp[i] = dp[i - 2] + 2;
//             }
            
//             if (ss[i - 1] == ')' && ss[i - 2] == ')') {
//                 if (i - 1 - dp[i - 1] - 1 >= 0 && ss[i - 1 - dp[i - 1] - 1] == '(') {  // "X...X))"
//                     dp[i] = dp[i - 1] + dp[i - 1 - dp[i - 1] - 1] + 2;
//                 }
//             }
            
//             max = Math.max(max, dp[i]);
//         }
        
//         return max;
//     }
// }


// stack O(n)time O(n)space
class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        
        int n = s.length();
        char[] ss = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);  // the bottom of the stack is the index of the parenthesis before a valid expression begins
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (ss[i] == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (!stack.isEmpty()) {
                    max = Math.max(max, i - stack.peek());
                } else {
                    stack.push(i);
                }
            }
        }
        
        return max;
    }
}