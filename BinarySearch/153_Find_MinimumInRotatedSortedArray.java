class Solution {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid;
            } else {
                high = mid;
            }
        }
        
        return Math.min(nums[low], nums[high]);
    }
}