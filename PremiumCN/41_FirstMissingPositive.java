class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null) {
            return 0;
        }
        
        int n = nums.length;
        int res = 1;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            while (num - 1 >= 0 && num - 1 < n && nums[num - 1] != num) {
                swap(i, num - 1, nums);
                num = nums[i];
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        
        return n + 1;
    }
                        
    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}