public class Solution {
    /**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0; 
        int right = nums.length - 1;
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            if (target <= nums[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        if (nums[left] == target) {
            return left;
        }
        
        if (nums[right] == target) {
            return right;
        }
        
        return -1;
    }
}