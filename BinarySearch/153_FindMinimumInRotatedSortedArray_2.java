class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        int mid;
        int target = nums[nums.length - 1];
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            if (nums[mid] < target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        return Math.min(nums[left], nums[right]);
    }
}