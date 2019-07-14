// Initital version
public class Solution {
    /**
     * @param A: An integer array
     * @return: An integer
     */
    public int stoneGame2(int[] A) {
        if (A == null || A.length <= 1) {
            return 0;
        }
        
        int n = A.length;
        
        int[][] dp = new int[n][n];
        int[] prefixSum = new int[n + 1];  // the sum of the first i numbers
        int sum = 0;
        
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i - 1];
            sum += A[i - 1];
        }
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n; i++) {
                int left = i;  // start position
                int right = i + len - 1;  // end position
                int min = Integer.MAX_VALUE;
                for (int mid = left; mid < right; mid++) {
                    min = Math.min(min, dp[left][mid % n] + dp[(mid  + 1) % n][right % n]);
                }
                
                dp[left][right % n] = min + getSum(left, right % n, sum, prefixSum);
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, dp[i][(i + n - 1) % n]);
        }
        
        return min;
    }
    
    private int getSum(int i, int j, int sum, int[] prefixSum) {
        if (i < j) {
            return prefixSum[j + 1] - prefixSum[i];
        } else {   //    j j+1  i-1 i
            return sum - (prefixSum[i] - prefixSum[j + 1]);
        }
    }
}


// public class Solution {
//     /**
//      * @param A: An integer array
//      * @return: An integer
//      */
//     public int stoneGame2(int[] A) {
//         if (A == null || A.length <= 1) {
//             return 0;
//         }
        
//         int n = A.length;
        
//         int[][] dp = new int[n][n];
//         int[] prefixSum = new int[2 * n + 1];  // the sum of the first i numbers
        
//         for (int i = 1; i <= 2 * n; i++) {
//             prefixSum[i] = prefixSum[i - 1] + A[(i - 1) % n];
//         }
        
//         for (int len = 2; len <= n; len++) {
//             for (int i = 0; i < n; i++) {
//                 int left = i;  // start position
//                 int right = i + len - 1;  // end position
//                 int min = Integer.MAX_VALUE;
//                 for (int mid = left; mid < right; mid++) {
//                     min = Math.min(min, dp[left][mid % n] + dp[(mid  + 1) % n][right % n]);
//                 }
                
//                 dp[left][right % n] = min + prefixSum[right + 1] - prefixSum[left];
//             }
//         }
        
//         int min = Integer.MAX_VALUE;
//         for (int i = 0; i < n; i++) {
//             min = Math.min(min, dp[i][(i + n - 1) % n]);
//         }
        
//         return min;
//     }
// }