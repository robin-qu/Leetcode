class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int n = nums.length;
        int furthest = 0;
        for (int i = 0; i < n; i++) {
            if (i <= furthest) {
                furthest = Math.max(furthest, i + nums[i]);
                if (furthest >= n - 1) {
                    return true;
                }
            } else {
                return false;
            }
        }

        return true;
    }
}