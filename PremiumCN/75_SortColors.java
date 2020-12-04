class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int curr = 0;
        while (curr <= right) {
            if (nums[curr] == 0) {
                swap(left++, curr++, nums);
            } else if (nums[curr] == 1) {
                curr++;
            } else {
                swap(right--, curr, nums);
            }
        }
    }

    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}