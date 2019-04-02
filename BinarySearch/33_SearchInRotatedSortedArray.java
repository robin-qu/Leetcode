class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int low = 0;
        int high = nums.length - 1;
        
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > nums[high]) {
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
        
        if (nums[low] == target) {
            return low;
        } else if (nums[high] == target) {
            return high;
        }
        return -1;
    }
}