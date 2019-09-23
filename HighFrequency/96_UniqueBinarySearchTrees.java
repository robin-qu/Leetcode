// // D&C O(n!)time O(n)space
// class Solution {
//     public int numTrees(int n) {
//         if (n == 0 || n == 1) {
//             return 1;
//         }
        
//         int res = 0;
//         for (int i = 1; i <= n; i++) {
//             res += numTrees(i - 1) * numTrees(n - i);
//         }
        
//         return res;
//     }
// }


// // D&C with memo O(n^2)time O(n)space
// class Solution {
//     public int numTrees(int n) {
//         int[] memo = new int[n + 1];
//         Arrays.fill(memo, -1);
        
//         return helper(n, memo);
//     }
    
//     private int helper(int n, int[] memo) {
//         if (memo[n] != -1) {
//             return memo[n];
//         }
        
//         if (n == 0 || n == 1) {
//             memo[n] = 1;
//             return 1;
//         }
        
//         int res = 0;
//         for (int i = 1; i <= n; i++) {
//             res += helper(i - 1, memo) * helper(n - i, memo);
//         }
        
//         return res;
//     }
// }


// DP O(n^2)time O(n)space
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
    
        return dp[n];
    }
}