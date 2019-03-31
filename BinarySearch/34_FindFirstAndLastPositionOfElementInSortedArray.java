class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        // Find starting position
        int low = 0;
        int high = nums.length - 1;
        
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                high = mid;
            } else if (nums[mid] > target) {
                high = mid;
            } else {
                low = mid;
            }
        }
        
        if (nums[low] == target) {
            res[0] = low;
        } else if (nums[high] == target) {
            res[0] = high;
        }
        
        // Find ending position
        high = nums.length - 1;
        
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                low = mid;
            } else if (nums[mid] > target) {
                high = mid;
            } else {
                low = mid;
            }
        }
        
        if (nums[high] == target) {
            res[1] = high;
        } else if (nums[low] == target) {
            res[1] = low;
        }
        
        return res;
    }
}