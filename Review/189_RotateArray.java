class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || k % nums.length == 0) {
            return;
        }

        int n = nums.length;
        k = k % n;
        rotate(nums, 0, n - 1);
        rotate(nums, 0, k - 1);
        rotate(nums, k, n - 1);
    }

    private void rotate(int[] nums, int start, int end) {
        int left = start;
        int right = end;
        while (left < right) {
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
            left++;
            right--;
        }
    }
}