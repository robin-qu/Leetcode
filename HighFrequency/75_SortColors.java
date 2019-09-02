// Two pointers O(n)time O(1)space
class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int i = 0;
        
        while (i <= right) {
            if (nums[i] == 0) {
                swap(nums, left++, i++);
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums, i, right--);
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}