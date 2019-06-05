class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int curr = 0;
        int left = 0;
        int right = nums.length - 1;
        
        while (curr <= right) {
            while (curr <= right && nums[curr] == 2) {
                swap(nums, curr, right);
                right--;
            }
            
            if (curr <= right && nums[curr] == 0) {
                swap(nums, curr, left);
                left++;
            }
            
            curr++;
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}