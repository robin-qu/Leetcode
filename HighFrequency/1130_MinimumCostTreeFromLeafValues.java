// // dp O(n^3)time O(n^2)space
// class Solution {
//     public int mctFromLeafValues(int[] arr) {
//         if (arr == null || arr.length == 0) {
//             return 0;
//         }
        
//         int n = arr.length;
        
//         int[][] max = new int[n][n];  // max[i][j] represents the max value between arr[i] and arr[j]
//         for (int i = 0; i < n; i++) {
//             max[i][i] = arr[i];
//             for (int j = i + 1; j < n; j++) {
//                 max[i][j] = Math.max(max[i][j - 1], arr[j]);
//             }
//         }
        
//         int[][] dp = new int[n][n];  // dp[i][j] represents the answer between i and j
//         for (int len = 2; len <= n; len++) {
//             for (int i = 0; i + len - 1 < n; i++) {
//                 int j = i + len - 1;
//                 dp[i][j] = Integer.MAX_VALUE;
//                 for (int k = i; k < j; k++) {
//                     int maxLeft = max[i][k];
//                     int maxRight = max[k + 1][j];
//                     dp[i][j] = Math.min(dp[i][j], maxLeft * maxRight + dp[i][k] + dp[k + 1][j]);
//                 }
//             }
//         }
        
//         return dp[0][n - 1];
//     }
// }


// dp O(n)time O(n)space
class Solution {
    public int mctFromLeafValues(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        int n = arr.length;
        int res = 0;
        
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        for (int num : arr) {
            while (stack.peek() < num) {
                int mid = stack.pop();
                res += mid * (Math.min(stack.peek(), num));
            }
            
            stack.push(num);
        }
        
        while (stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        
        return res;
    }
}