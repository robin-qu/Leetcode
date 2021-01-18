// class Solution {
//     public int jump(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }

//         int n = nums.length;

//         int[] dp = new int[n];
//         Arrays.fill(dp, Integer.MAX_VALUE);
//         dp[0] = 0;
//         for (int i = 0; i < n; i++) {
//             if (dp[i] == Integer.MAX_VALUE) {
//                 continue;
//             }
//             for (int j = 1; j <= nums[i] && i + j < n; j++) {
//                 dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
//             }
//         }

//         return dp[n - 1];
//     }
// }


class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int furthest = 0;
        int res = 0;
        int curr = 0;
        while (furthest < n - 1) {
            int newFurthest = 0;
            while (curr <= furthest) {
                newFurthest = Math.max(newFurthest, curr + nums[curr]);
                curr++;
            }
            furthest = newFurthest;
            res++;
        }
        return res;
    }
}