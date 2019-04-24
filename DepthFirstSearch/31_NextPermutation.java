class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        // Finds the last ascending position
        int idx = nums.length - 1;
        while (idx > 0 && nums[idx - 1] >= nums[idx]) {
            idx--;
        }
        
        if (idx == 0) {
            Arrays.sort(nums);
            return;
        }
        
        // Finds the first bigger value
        int i = nums.length - 1;
        while (i >= idx && nums[idx - 1] >= nums[i]) {
            i--;
        }
        
        int temp = nums[i];
        nums[i] =  nums[idx - 1];
        nums[idx - 1] = temp;
        Arrays.sort(nums, idx, nums.length);
    }
}