// // dp O(n^2)time O(n)space
// class Solution {
//     public int lengthOfLIS(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int n = nums.length;
        
//         int[] dp = new int[n];  // dp[i] represents the longest length of increasing subsequence ended with i-th element
//         Arrays.fill(dp, 1);
        
//         int max = 1;
//         for (int i = 1; i < n; i++) {
//             for (int j = 0; j < i; j++) {
//                 if (nums[i] > nums[j]) {
//                     dp[i] = Math.max(dp[i], dp[j] + 1);
//                 }
//             }
            
//             max = Math.max(max, dp[i]);
//         }
        
//         return max;
//     }
// }


// dp + binarySearch O(nlogn)time O(n)space
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = Integer.MIN_VALUE;
        
        int max = 1;
        for (int i = 0; i < n; i++) {
            int idx = binarySearch(nums[i], dp);
            dp[idx] = nums[i];
            max = Math.max(idx, max);
        }
        
        return max;
    }
    
    // finds the index of the first element that is bigger than target
    private int binarySearch(int target, int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
            
        if (nums[left] > target) {
            return left;
        }
        
        return right;
    }
}