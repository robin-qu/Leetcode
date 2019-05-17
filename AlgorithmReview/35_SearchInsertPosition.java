class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            if (target < nums[mid]) {
                right = mid;
            } else if (target > nums[mid]) {
                left = mid;
            } else {
                return mid;
            }
        }
        
        if (nums[right] < target) {
            return right + 1;
        }
        
        if (nums[left] < target) {
            return left + 1;
        }
        
        return left;
    }
}