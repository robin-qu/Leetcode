class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int n = nums.length;

        boolean up = true;
        for (int i = 1; i < n; i++) {
            if ((up && nums[i] < nums[i - 1]) || (!up && nums[i] > nums[i - 1])) {
                int temp = nums[i - 1];
                nums[i - 1] = nums[i];
                nums[i] = temp;
            }
            up = !up;
        }
    }
}