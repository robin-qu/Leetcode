class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int left = 0;
        int right = nums.length - 1;
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            if (nums[mid] == nums[right]) {
                right--;
            } else if (nums[mid] > nums[right]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid;
                } else {
                    left = mid;
                }
            } else {
                if (target <= nums[right] && target > nums[mid]) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }
        
        return nums[left] == target || nums[right] == target;
    }
}