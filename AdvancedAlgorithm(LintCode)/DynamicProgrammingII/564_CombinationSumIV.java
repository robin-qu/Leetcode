// public class Solution {
//     /**
//      * @param nums: an integer array and all positive numbers, no duplicates
//      * @param target: An integer
//      * @return: An integer
//      */
//     public int backPackVI(int[] nums, int target) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int n = nums.length;
        
//         int[][] dp = new int[target + 1][n + 1];  // dp[i][j] represents the number of possible combinations of the first j numbers in nums that add up tp i
//         Arrays.fill(dp[0], 1);  // only one combination to fill 0
        
//         for (int i = 0; i <= target; i++) {
//             for (int j = 1; j <= n; j++) {
//                 dp[i][j] = dp[i][j - 1];
                
//                 if (nums[j - 1] <= i) {
//                     dp[i][j] += dp[i - nums[j - 1]][n];
//                 }
//             }
//         }
        
//         return dp[target][n];
//     }
// }


// DP: Similar to climbing stairs
public class Solution {
    /**
     * @param nums: an integer array and all positive numbers, no duplicates
     * @param target: An integer
     * @return: An integer
     */
    public int backPackVI(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        
        for (int i = 0; i <= target; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }
        
        return dp[target];
    }
}