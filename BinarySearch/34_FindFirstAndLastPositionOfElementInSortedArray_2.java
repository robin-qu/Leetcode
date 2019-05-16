class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        int left = 0;
        int right = nums.length - 1;
        int mid;
        
        // find the first
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            if (target <= nums[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        if (nums[left] == target) {
            res[0] = left;
        } else if (nums[right] == target) {
            res[0] = right;
        }
        
        left = 0;
        right = nums.length - 1;
        
        // find the last
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            if (target < nums[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        if (nums[right] == target) {
            res[1] = right;
        } else if (nums[left] == target) {
            res[1] = left;
        }
        
        return res;
    }
}