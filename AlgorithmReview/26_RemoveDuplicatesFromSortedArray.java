class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = 1;
        int n = nums.length;
        
        while (right < n) {
            if (nums[right] != nums[left]) {
                left++;
                nums[left] = nums[right];
            }
            right++;
        }
        
        return left + 1;
    }
}