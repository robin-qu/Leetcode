// // Backtracking dfs (TLE)
// class Solution {
//     public boolean canJump(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return false;
//         }
        
//         return backTracking(nums, 0);
//     }
    
//     private boolean backTracking(int[] nums, int pos) {
//         if (pos >= nums.length - 1) {
//             return true;
//         }
        
//         for (int i = nums[pos]; i > 0; i--) {
//             pos += i;
//             if (backTracking(nums, pos)) {
//                 return true;
//             }
//             pos -= i;
//         }
        
//         return false;
//     }
// }


// // DP backward
// class Solution {
//     public boolean canJump(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return false;
//         }
        
//         int len = nums.length;
//         boolean[] dp = new boolean[len];
        
//         dp[len - 1] = true;
        
//         for (int i = len - 2; i >= 0; i--) {
//             int maxStep = nums[i];
//             for (int j = i; j <= Math.min(i + maxStep, len - 1); j++) {
//                 if (dp[j] == true) {
//                     dp[i] = true;
//                     break;
//                 }
//             }
//         }
        
//         return dp[0];
//     }
// }


// // DP  forward
// class Solution {
//     public boolean canJump(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return false;
//         }
        
//         int len = nums.length;
//         boolean[] dp = new boolean[len];
        
//         dp[0] = true;
        
//         for (int i = 1; i < len; i++) {
//             for (int j = 0; j < i; j++) {
//                 if (dp[j] && j + nums[j] >= i) {
//                     dp[i] = true;
//                     break;
//                 }
//             }
//         }
        
//         return dp[len - 1];
//     }
// }


// // DP memory optimization
// // continuous, all of the positions before a reachable position are reachable
// class Solution {
//     public boolean canJump(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return false;
//         }
        
//         int len = nums.length;
        
//         for (int i = 1; i < len; i++) {
//             boolean curr = false;
//             for (int j = 0; j < i; j++) {
//                 if (j + nums[j] >= i) {
//                     curr = true;
//                     break;
//                 }
//             }
            
//             if (!curr) {
//                 return false;
//             }
//         }
        
//         return true;
//     }
// }


// // Greedy forward
// class Solution {
//     public boolean canJump(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return false;
//         }
        
//         int right = nums[0];
//         int len = nums.length;
        
//         for (int i = 1; i < len; i++) {
//             if (i <= right && i + nums[i] >= right) {
//                 right = i + nums[i];
//             }
//         }
        
//         return right >= len - 1;
//     }
// }

// Greedy backward
class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int len = nums.length;
        int left = len - 1;
        
        for (int i = len - 1; i >= 0; i--) {
            if (i + nums[i] >= left) {
                left = i;
            }
        }
        
        return left <= 0;
    }
}