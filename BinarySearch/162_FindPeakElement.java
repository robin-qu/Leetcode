class Solution {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        if (nums.length == 1) {
            return 0;
        }
        
        int low = 0;
        int high = nums.length - 1;
        
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < nums[mid + 1]) {
                low = mid;
            } else {
                high = mid;
            }
        }
        
        if (nums[low] > nums[low + 1]) {
            return low;
        }
        return high;
    }
}