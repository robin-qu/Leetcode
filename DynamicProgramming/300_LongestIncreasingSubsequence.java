// // DP
// class Solution {
//     public int lengthOfLIS(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int len = nums.length;
//         int[] dp = new int[len];
//         for (int i = 0; i < len; i++) {
//             dp[i] = 1;
//         }
        
//         for (int i = 0; i < len; i++) {
//             for (int j = 0; j < i; j++) {
//                 if (nums[j] < nums[i]) {
//                     dp[i] = Math.max(dp[i], dp[j] + 1);
//                 }
//             }
//         }
        
//         int res = Integer.MIN_VALUE;
//         for (int i = 0; i < len; i++) {
//             res = Math.max(res, dp[i]);
//         }
        
//         return res;
//     }
// }

// DP + Binary Search
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int len = nums.length;
        int[] dp = new int[len + 1];
        dp[0] = Integer.MIN_VALUE;
        for (int i = 1; i < len + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        
        for (int i = 0; i < len; i++) {
            int idx = binarySearch(dp, nums[i]);
            dp[idx] = nums[i];
        }
        
        for (int i = len; i >= 0; i--) {
            if (dp[i] != Integer.MAX_VALUE) {
                return i;
            }
        }
        
        return 0;
    }
    
    private int binarySearch(int[] nums, int value) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (value < nums[mid]) {
                right = mid;
            } else if (nums[mid] < value) {
                left = mid;
            } else {
                return mid;
            }
        }
        
        return right;
    }
}