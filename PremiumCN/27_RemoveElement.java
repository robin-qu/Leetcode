class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != val) {
                nums[res++] = nums[i];
            }
        }

        return res;
    }
}