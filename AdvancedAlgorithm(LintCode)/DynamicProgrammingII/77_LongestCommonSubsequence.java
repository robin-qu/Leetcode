// // DP O(mn) space
// public class Solution {
//     /**
//      * @param A: A string
//      * @param B: A string
//      * @return: The length of longest common subsequence of A and B
//      */
//     public int longestCommonSubsequence(String A, String B) {
//         if (A == null || A.length() == 0 || 
//             B == null || B.length() == 0) {
//             return 0;
//         }
        
//         char[] a = A.toCharArray();
//         char[] b = B.toCharArray();
//         int m = a.length;
//         int n = b.length;
        
//         int[][] dp = new int[m + 1][n + 1];  // dp[i][j] represents the length of the longest common subsequence of the first i characters of A and the first j characters of B
        
//         // Initialization
//         for (int i = 0; i <= m; i++) {
//             for (int j = 0; j <= n; j++) {
//                 if (i == 0 || j == 0) {
//                     dp[i][j] = 0;
//                 }
//             }
//         }
        
//         for (int i = 1; i <= m; i++) {
//             for (int j = 1; j <= n; j++) {
//                 dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
//                 if (a[i - 1] == b[j - 1]) {
//                     dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
//                 }
//             }
//         }
        
//         return dp[m][n];
//     }
// }


// // DP O(n) space
// public class Solution {
//     /**
//      * @param A: A string
//      * @param B: A string
//      * @return: The length of longest common subsequence of A and B
//      */
//     public int longestCommonSubsequence(String A, String B) {
//         if (A == null || A.length() == 0 || 
//             B == null || B.length() == 0) {
//             return 0;
//         }
        
//         char[] a = A.toCharArray();
//         char[] b = B.toCharArray();
//         int m = a.length;
//         int n = b.length;
        
//         int[][] dp = new int[2][n + 1];  // dp[i][j] represents the length of the longest common subsequence of the first i characters of A and the first j characters of B
        
//         // Initialization
//         Arrays.fill(dp[0], 0);
//         dp[1][0] = 0;
//         int old = 1;
//         int now = 0;
        
//         for (int i = 1; i <= m; i++) {
//             old = now;
//             now = 1 - old;
//             for (int j = 1; j <= n; j++) {
//                 dp[now][j] = Math.max(dp[old][j], dp[now][j - 1]);
//                 if (a[i - 1] == b[j - 1]) {
//                     dp[now][j] = Math.max(dp[now][j], dp[old][j - 1] + 1);
//                 }
//             }
//         }
        
//         return dp[now][n];
//     }
// }


// // DP O(n) space with LCS printed out
// public class Solution {
//     /**
//      * @param A: A string
//      * @param B: A string
//      * @return: The length of longest common subsequence of A and B
//      */
//     public int longestCommonSubsequence(String A, String B) {
//         if (A == null || A.length() == 0 || 
//             B == null || B.length() == 0) {
//             return 0;
//         }
        
//         char[] a = A.toCharArray();
//         char[] b = B.toCharArray();
//         int m = a.length;
//         int n = b.length;
        
//         int[][] dp = new int[2][n + 1];  // dp[i][j] represents the length of the longest common subsequence of the first i characters of A and the first j characters of B
//         int[][] print = new int[m + 1][n + 1];  // used to record the choice of each step in order to print the result out
        
//         // Initialization
//         Arrays.fill(dp[0], 0);
//         dp[1][0] = 0;
//         int old = 1;
//         int now = 0;
        
//         for (int i = 1; i <= m; i++) {
//             old = now;
//             now = 1 - old;
//             for (int j = 1; j <= n; j++) {
//                 dp[now][j] = Math.max(dp[old][j], dp[now][j - 1]);
//                 if (dp[now][j] == dp[old][j]) {
//                     print[i][j] = 1;
//                 } else {
//                     print[i][j] = -1;
//                 }
//                 if (a[i - 1] == b[j - 1]) {
//                     dp[now][j] = Math.max(dp[now][j], dp[old][j - 1] + 1);
//                     if (dp[now][j] == dp[old][j - 1] + 1) {
//                         print[i][j] = 0;
//                     }
//                 }
//             }
//         }
        
//         char[] res = new char[dp[now][n]];
//         int i = m;
//         int j = n;
//         int p = dp[now][n] - 1;
        
//         while (i > 0 && j > 0) {
//             if (print[i][j] == 1) {
//                 i--;
//             } else if (print[i][j] == -1) {
//                 j--;
//             } else {
//                 res[p] = a[i - 1];
//                 i--;
//                 j--;
//                 p--;
//             }
//         }
        
//         System.out.println("Longest Common Subsequence is: ");
//         for (char c : res) {
//             System.out.print(c);
//         }
//         System.out.println();
        
//         return dp[now][n];
//     }
// }


// Recurrsive dfs O(mn) space
public class Solution {
    /**
     * @param A: A string
     * @param B: A string
     * @return: The length of longest common subsequence of A and B
     */
    public int longestCommonSubsequence(String A, String B) {
        if (A == null || A.length() == 0 || 
            B == null || B.length() == 0) {
            return 0;
        }
        
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        int m = a.length;
        int n = b.length;
        
        int[][] memo = new int[m + 1][n + 1];  // dp[i][j] represents the length of the longest common subsequence of the first i characters of A and the first j characters of B
        
        return dfs(a, m, b, n, memo);
    }
    
    private int dfs(char[] a, int i, char[] b, int j, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        
        if (i == 0 || j == 0) {
            return memo[i][j] = 0;
        }
        
        if (a[i - 1] == b[j - 1]) {
            return memo[i][j] = 1 + dfs(a, i - 1, b, j - 1, memo);
        }
        
        return memo[i][j] = Math.max(dfs(a, i - 1, b, j, memo), dfs(a, i, b, j - 1, memo));
    }
}