// // PrefixSum
// public class Solution {
//     /**
//      * @param nums: A list of integers
//      * @return: A integer indicate the sum of max subarray
//      */
//     public int maxSubArray(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int n = nums.length;
//         int max = Integer.MIN_VALUE;
//         int sum = 0;
//         int prefixSum = 0;
        
//         for (int i = 0; i < n; i++) {
//             if (prefixSum <= 0) {
//                 sum = nums[i];
//                 prefixSum = 0;
//             } else {
//                 sum += nums[i];
//             }
//             max = Math.max(sum, max);
//             prefixSum += nums[i];
//         }
        
//         return max;
//     }
// }


// DP
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0] >= 0 ? nums[0] : 0;
        int max = nums[0];
        
        for (int i = 1; i < n; i++) {
            dp[i] = (dp[i - 1] >= 0 ? dp[i - 1] + nums[i]: nums[i]);
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}