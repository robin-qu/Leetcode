// // DP O(n^2)time O(n)space
// class Solution {
//     public int jump(int[] nums) {
//         if (nums == null || nums.length <= 1) {
//             return 0;
//         }
        
//         int n = nums.length;
        
//         int[] dp = new int[n];
//         for (int i = 1; i < n; i++) {
//             dp[i] = Integer.MAX_VALUE;
//             for (int j = i - 1; j >= 0; j--) {
//                 if (j + nums[j] >= i) {
//                     dp[i] = Math.min(dp[i], 1 + dp[j]);
//                 }
//             }
//         }
        
//         return dp[n - 1];
//     }
// }


// Greedy O(n)time O(1)space
class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        
        int n = nums.length;
        int right = 0;  // right boundary
        int left = 0;  // left boundary
        int count = 0;
        
        while (right < n - 1) {
            int newRight = right;
            for (int i = left; i <= right; i++) {
                newRight = Math.max(newRight, i + nums[i]);
            }
            left = right + 1;
            right = newRight;
            count++;
        }
        
        return count;
    }
}