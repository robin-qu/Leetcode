// // O(n) space
// public class Solution {
//     /**
//      * @param A: An array of Integer
//      * @return: an integer
//      */
//     public int longestIncreasingContinuousSubsequence(int[] A) {
//         if (A == null || A.length == 0) {
//             return 0;
//         }
        
//         int n = A.length;
//         int[] dp = new int[n];
//         int max = 1;
        
//         Arrays.fill(dp, 1);
//         // left to right
//         for (int i = 1; i < n; i++) {
//             if (A[i] > A[i - 1]) {
//                 dp[i] = dp[i - 1] + 1;
//                 max = Math.max(max, dp[i]);
//             }
//         }
        
//         Arrays.fill(dp, 1);
//         // right to left
//         for (int i = n - 2; i >= 0; i--) {
//             if (A[i] > A[i + 1]) {
//                 dp[i] = dp[i + 1] + 1;
//                 max = Math.max(max, dp[i]);
//             }
//         }
        
//         return max;
//     }
// }

// // Optimized O(1) space
// public class Solution {
//     /**
//      * @param A: An array of Integer
//      * @return: an integer
//      */
//     public int longestIncreasingContinuousSubsequence(int[] A) {
//         if (A == null || A.length == 0) {
//             return 0;
//         }
        
//         int n = A.length;
//         int first = 1;
//         int second = 1;
//         int max = 1;
        
//         // left to right
//         for (int i = 1; i < n; i++) {
//             if (A[i] > A[i - 1]) {
//                 second = first + 1;
//                 max = Math.max(max, second);
//                 first = second;
//             } else {
//                 first = 1;
//             }
//         }
        
//         first = 1;
//         second = 1;
//         // right to left
//         for (int i = n - 2; i >= 0; i--) {
//             if (A[i] > A[i + 1]) {
//                 second = first + 1;
//                 max = Math.max(max, second);
//                 first = second;
//             } else {
//                 first = 1;
//             }
//         }
        
//         return max;
//     }
// }

// One iteration
public class Solution {
    /**
     * @param A: An array of Integer
     * @return: an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int n = A.length;
        int[] increasingDP = new int[n];
        int[] decreasingDP = new int[n];
        Arrays.fill(increasingDP, 1);
        Arrays.fill(decreasingDP, 1);
        int max = 1;
        
        for (int i = 0; i < n; i++) {
            if (i - 1 >= 0 && A[i] > A[i - 1]) {
                increasingDP[i] = increasingDP[i - 1] + 1;
                max = Math.max(max, increasingDP[i]);
            }
            
            if (i + 1 < n && A[i] > A[i + 1]) {
                decreasingDP[i + 1] = decreasingDP[i] + 1;
                max = Math.max(max, decreasingDP[i + 1]);
            }
        }
        
        return max;
    }
}