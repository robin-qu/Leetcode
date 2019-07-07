// // O(n) space
// public class Solution {
//     /**
//      * @param A: An array of non-negative integers
//      * @return: The maximum amount of money you can rob tonight
//      */
//     public long houseRobber(int[] A) {
//         if (A == null || A.length == 0) {
//             return 0l;
//         }
        
//         int n = A.length;
//         long[] dp = new long[n + 1];  // max amount of money robbed at house i
        
//         dp[0] = 0;
//         dp[1] = A[0];
        
//         for (int i = 2; i <= n; i++) {
//             dp[i] = Math.max(A[i - 1] + dp[i - 2], dp[i - 1]);
//         }
        
//         return dp[n];
//     }
// }


// O(1) space
public class Solution {
    /**
     * @param A: An array of non-negative integers
     * @return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
        if (A == null || A.length == 0) {
            return 0l;
        }
        
        int n = A.length;
        long first = 0l;
        long second = A[0];
        
        for (int i = 2; i <= n; i++) {
            long temp = second;
            second = Math.max(A[i - 1] + first, second);
            first = temp;
        }
        
        return second;
    }
}