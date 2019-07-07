// // Two passes, O(n) space
// public class Solution {
//     /**
//      * @param nums: An array of non-negative integers.
//      * @return: The maximum amount of money you can rob tonight
//      */
//     public int houseRobber2(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int n = nums.length;
//         if (n == 1) {
//             return nums[0];
//         }
        
//         int[] dp = new int[n + 1];  // max amount of money robbed at house i
//         dp[0] = 0;
//         dp[1] = nums[0];  // include first house
        
//         for (int i = 2; i < n; i++) {
//             dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
//         }
//         int max1 = dp[n - 1];  // max money with the first house robbed
        
//         dp[1] = 0;  // exclude first house
//         for (int i = 2; i <= n; i++) {
//             dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
//         }
//         int max2 = dp[n];  // max money with the first house not robbed
        
//         return Math.max(max1, max2);
//     }
// }


// One pass, O(1) space
public class Solution {
    /**
     * @param nums: An array of non-negative integers.
     * @return: The maximum amount of money you can rob tonight
     */
    public int houseRobber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        
        int max1 = rob(nums, 0, n - 2);
        int max2 = rob(nums, 1, n - 1);
        
        return Math.max(max1, max2);
    }
    
    private int rob(int[] nums, int left, int right) {
        int n = right - left + 1;
        int first = 0;
        int second = nums[left];
        
        for (int i = 1; i < right - left + 1; i++) {
            int temp = second;
            second = Math.max(nums[left + i] + first, second);
            first = temp;
        }
        
        return second;
    }
}