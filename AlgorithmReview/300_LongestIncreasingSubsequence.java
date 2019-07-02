// class Solution {
//     /**
//      * @param nums: An integer array
//      * @return: The length of LIS (longest increasing subsequence)
//      */
//     public int lengthOfLIS(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int n = nums.length;
//         int[] dp = new int[n];
//         for (int i = 0; i < n; i++) {
//             dp[i] = 1;
//             for(int j = 0; j < i; j++) {
//                 if (nums[j] < nums[i]) {
//                     dp[i] = Math.max(dp[j] + 1, dp[i]);
//                 }
//             }
//         }
        
//         int max = dp[0];
//         for (int i = 1; i < n; i++) {
//             max = Math.max(max, dp[i]);
//         }
        
//         return max;
//     }
// }


// Binary Search
class Solution {
    /**
     * @param nums: An integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        
        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            int idx = binarySearch(dp, nums[i]);
            maxLen = Math.max(maxLen, idx);
            dp[idx] = nums[i];
        }
        
        return maxLen;
    }
    
    private int binarySearch(int[] dp, int val) {
        int left = 0;
        int right = dp.length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (dp[mid] >= val) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        return dp[left] >= val ? left : right;
    }
}