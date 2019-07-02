// // DP
// class Solution {
//     /**
//      * @param A: A list of integers
//      * @return: An integer
//      */
//     public int jump(int[] A) {
//         if (A == null || A.length == 0) {
//             return 0;
//         }
        
//         int n = A.length;
//         int[] dp = new int[n];
//         dp[0] = 0;
        
//         for (int i = 1; i < n; i++) {
//             dp[i] = Integer.MAX_VALUE;
//             for (int j = 0; j < i; j++) {
//                 if (dp[j] != Integer.MAX_VALUE && A[j] >= i - j) {
//                     dp[i] = Math.min(dp[i], dp[j] + 1);
//                 }
//             }
//         }
        
//         return dp[n - 1];
//     }
// }

// Greedy
class Solution {
    /**
     * @param A: A list of integers
     * @return: An integer
     */
    public int jump(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int n = A.length;
        int left = 0;  //  [left ... right] is the reachable
        int right = 0; //  interval at the count-th step
        int count = 0;
        
        while (right < n - 1) {
            count++;
            int farthest = right;
            for (int i = left; i <= right; i++) {
                farthest = Math.max(A[i] + i, farthest);  // the farthest position that all of the positions in the interval can reach
            }
            
            // update interval
            left = right + 1;
            right = farthest;
        }
        
        return count;
    }
}