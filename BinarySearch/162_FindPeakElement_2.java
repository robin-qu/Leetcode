class Solution {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = nums.length - 1;
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            if (mid + 1 < nums.length && nums[mid] < nums[mid + 1]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return nums[left] > nums[right] ? left : right;
    }
}