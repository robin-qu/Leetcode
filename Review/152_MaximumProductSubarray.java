class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;

        int[] maxs = new int[n];
        int[] mins = new int[n];
        maxs[0] = mins[0] = nums[0];
        int res = nums[0];

        for (int i = 1; i < n; i++) {
            if (nums[i] >= 0) {
                maxs[i] = Math.max(nums[i], nums[i] * maxs[i - 1]);
                mins[i] = Math.min(nums[i], nums[i] * mins[i - 1]);
            } else {
                maxs[i] = Math.max(nums[i], nums[i] * mins[i - 1]);
                mins[i] = Math.min(nums[i], nums[i] * maxs[i - 1]);
            }
            res = Math.max(res, maxs[i]);
        }

        return res;
    }
}