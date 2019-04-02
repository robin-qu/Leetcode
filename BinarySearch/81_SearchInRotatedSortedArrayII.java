class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int low = 0;
        int high = nums.length - 1;
        
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return true;
            }
            
            if (nums[mid] == nums[high]) {
                high--;
            } else if (nums[mid] > nums[high]) {
                if (target < nums[mid] && target >= nums[low]) {
                    high = mid;
                } else {
                    low = mid;
                }
            } else {
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid;
                } else {
                    high = mid;
                }
            }
        }
        
        if (nums[low] == target || nums[high] == target) {
            return true;
        }
        return false;
    }
}
