public class Solution {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public int lastPosition(int[] nums, int target) {
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
            } else {
                left = mid;
            }
        }
        
        if (nums[right] == target) {
            return right;
        }
        
        if (nums[left] == target) {
            return left;
        }
        
        return -1;
    }
}