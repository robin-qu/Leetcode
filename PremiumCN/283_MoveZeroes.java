class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int n = nums.length;

        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] != 0) {
                nums[left++] = nums[right];
            }
        }

        while (left < n) {
            nums[left++] = 0;
        }
    }
}