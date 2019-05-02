public class Solution {
    /*
     * @param nums: an array of integers
     * @return: nothing
     */
    public void partitionArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            while (left < right && nums[left] % 2 == 1) {
                left++;
            }
            
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            
            if (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}