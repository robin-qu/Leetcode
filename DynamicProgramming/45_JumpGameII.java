// // Initial version
// class Solution {
//     public int jump(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int len = nums.length;
//         int[] dp = new int[len];
        
//         for (int i = len - 2; i >= 0; i--) {
//             int step = nums[i];
//             if (step == 0) {
//                 dp[i] = -1;
//                 continue;
//             }
            
//             int min = Integer.MAX_VALUE;
//             for (int j = step; j > 0; j--) {
//                 if (i + j < len && dp[i + j] >= 0) {
//                     min = Math.min(dp[i + j] + 1, min);
//                 }
//             }
//             if (min == Integer.MAX_VALUE) {
//                 min = -1;
//             }
            
//             dp[i] = min;
//         }
        
//         return dp[0];
//     }
// }


// // Backward DP
// class Solution {
//     public int jump(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int len = nums.length;
//         int[] dp = new int[len];
//         for (int i = 0; i < len - 1; i++) {
//             dp[i] = Integer.MAX_VALUE;
//         }
        
//         for (int i = len - 2; i >= 0; i--) {
//             for (int j = nums[i]; j > 0; j--) {
//                 if (i + j < len && dp[i + j] != Integer.MAX_VALUE) {
//                     dp[i] = Math.min(dp[i + j] + 1, dp[i]);
//                 }
//             }
//         }
        
//         return dp[0];
//     }
// }

// // Forward DP  (TLE)
// class Solution {
//     public int jump(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int len = nums.length;
//         int[] dp = new int[len];
//         for (int i = 1; i < len; i++) {
//             dp[i] = Integer.MAX_VALUE;
//         }
        
//         for (int i = 1; i < len; i++) {
//             for (int j = 0; j < i; j++) {
//                 if (dp[j] != Integer.MAX_VALUE && j + nums[j] >= i) {
//                     dp[i] = Math.min(dp[i], dp[j] + 1);
//                 }
//             }
//         }
        
//         return dp[len - 1];
//     }
// }

// Greedy
class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int len = nums.length;
        int start = 0;
        int end = 0;
        int step = 0;
        
        while (end < len - 1) {
            step++;
            int farthest = end;
            for (int i = start; i <= end; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }
            
            start = end + 1;
            end = farthest;
        }
        
        return step;
    }
}