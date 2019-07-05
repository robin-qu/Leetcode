// // 2D DP
// public class Solution {
//     /**
//      * @param nums: An array of integers
//      * @return: An integer
//      */
//     public int maxProduct(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int n = nums.length;
//         int[][] dp = new int[n][n];
//         int max = Integer.MIN_VALUE;
        
//         for (int len = 1; len <= n; len++) {
//             for (int i = 0; i + len - 1 < n; i++) {
//                 int left = i;
//                 int right = i + len - 1;
//                 if (len == 1) {
//                     dp[left][right] = nums[left];
//                 } else {
//                     dp[left][right] = dp[left][right - 1] * nums[right];
//                 }
                
//                 max = Math.max(max, dp[left][right]);
//             }
//         }
        
//         return max;
//     }
// }


// // 1D DP O(n) space
// public class Solution {
//     /**
//      * @param nums: An array of integers
//      * @return: An integer
//      */
//     public int maxProduct(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int n = nums.length;
//         int[] maxDP = new int[n];
//         maxDP[0] = nums[0];
//         int[] minDP = new int[n];
//         minDP[0] = nums[0];
//         int max = nums[0];
        
//         for (int i = 1; i < n; i++) {
//             int curr = nums[i];
//             if (curr > 0) {
//                 maxDP[i] = Math.max(curr, maxDP[i - 1] * curr);
//                 minDP[i] = Math.min(curr, minDP[i - 1] * curr);
//             } else {
//                 maxDP[i] = Math.max(curr, minDP[i - 1] * curr);
//                 minDP[i] = Math.min(curr, maxDP[i - 1] * curr);
//             }
            
//             max = Math.max(max, maxDP[i]);
//         }
        
//         return max;
//     }
// }

// 1D DP O(n) space
public class Solution {
    /**
     * @param nums: An array of integers
     * @return: An integer
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int currMax = nums[0];
        int currMin = nums[0];
        int max = nums[0];
        
        for (int i = 1; i < n; i++) {
            int curr = nums[i];
            if (curr > 0) {
                currMax = Math.max(curr, currMax * curr);
                currMin = Math.min(curr, currMin * curr);
            } else {
                int temp = currMax;
                currMax = Math.max(curr, currMin * curr);
                currMin = Math.min(curr, temp * curr);
            }
            
            max = Math.max(max, currMax);
        }
        
        return max;
    }
}