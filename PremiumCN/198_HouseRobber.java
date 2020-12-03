class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int prev = Math.max(nums[0], nums[1]);
        int prevPrev = nums[0];
        for (int i = 2; i < n; i++) {
            int temp = prev;
            prev = Math.max(nums[i] + prevPrev, prev);
            prevPrev = temp;
        }

        return prev;
    }
}