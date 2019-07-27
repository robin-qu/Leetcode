// Binary Search O(logn)time O(1)space
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[left]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid;
                } else if (nums[mid] < target || target < nums[left]) {
                    left = mid;
                } else {
                    return mid;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid;
                } else if (nums[right] < target || target < nums[mid]) {
                    right = mid;
                } else {
                    return mid;
                }
            }
        }
        
        // post processing
        if (nums[left] == target) {
            return left;
        }
        
        if (nums[right] == target) {
            return right;
        }
        
        return -1;        
    }
}