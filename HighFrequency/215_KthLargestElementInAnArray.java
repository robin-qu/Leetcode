// Quick select bestO(n)time O(1)time
class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return -1;
        }
        
        return quickSelect(0, nums.length - 1, nums, k);
    }
    
    private int quickSelect(int start, int end, int[] nums, int k) {
        int left = start;
        int right = end;
        int pivot = nums[left + (right - left) / 2];
        
        while (left <= right) {
            while (left <= right && nums[left] > pivot) {
                left++;
            }
            
            while (left <= right && nums[right] < pivot) {
                right--;
            }
            
            if (left <= right) {
                swap(left, right, nums);
                left++;
                right--;
            }
        }
        
        if (k - 1 <= right) {
            return quickSelect(start, right, nums, k);
        } else if (k - 1 >= left) {
            return quickSelect(left, end, nums, k);
        } else {
            return nums[k - 1];
        }
    }
    
    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}