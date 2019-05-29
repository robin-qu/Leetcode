// // prefixsum
// class Solution {
//     public int maxSubArray(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int prefixSum = 0;
//         int minPrefixSum = 0;
//         int max = Integer.MIN_VALUE;
        
//         for (int i = 0; i < nums.length; i++) {
//             prefixSum += nums[i];
//             max = Math.max(max, prefixSum - minPrefixSum);
//             minPrefixSum = Math.min(prefixSum, minPrefixSum);
//         }
        
//         return max;
//     }
// }


// // prefixsum
// class Solution {
//     public int maxSubArray(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int sum = 0;
//         int maxSum = Integer.MIN_VALUE;
//         for (int i = 0; i < nums.length; i++) {
//             sum += nums[i];
//             maxSum = Math.max(maxSum, sum);
//             if (sum < 0) {
//                 sum = 0;
//             }
//         }
        
//         return maxSum;
//     }
// }

// divide and conquer
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        return helper(nums, 0, nums.length - 1);
    }
    
    private int helper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        
        int mid = left + (right - left) / 2;
        
        int leftMax = helper(nums, left, mid);
        int rightMax = helper(nums, mid + 1, right);
        
        int midleftMax = Integer.MIN_VALUE;
        int midleftSum = 0;
        for (int i = mid; i >= left; i--) {
            midleftSum += nums[i];
            midleftMax = Math.max(midleftMax, midleftSum);
        }
        
        int midrightMax = Integer.MIN_VALUE;
        int midrightSum = 0;
        for (int i = mid + 1; i <= right; i++) {
            midrightSum += nums[i];
            midrightMax = Math.max(midrightMax, midrightSum);
        }
        
        return Math.max(midleftMax + midrightMax, Math.max(leftMax, rightMax));
    }
}