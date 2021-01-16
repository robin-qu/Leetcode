class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int n = nums.length;
        int idx = n - 1;
        while (idx > 0 && nums[idx - 1] >= nums[idx]) {
            idx--;
        }

        if (idx == 0) {
            reverse(0, n - 1, nums);
            return;
        }
        for (int i = n - 1; i >= idx; i--) {
            if (nums[i] > nums[idx - 1]) {
                swap(i, idx - 1, nums);
                reverse(idx, n - 1, nums);
                return;
            }
        }
    }

    private void reverse(int i, int j, int[] nums) {
        while (i < j) {
            swap(i++, j--, nums);
        }
    }

    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}