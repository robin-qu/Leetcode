class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        
        return quickSelect(0, n - 1, nums, k - 1);
    }
                                        
    private int quickSelect(int start, int end, int[] nums, int k) {
        int pivot = nums[start + (end - start) / 2];
        int left = start;
        int right = end;
        while (left <= right) {
            while (left <= right && nums[left] > pivot) {
                left++;
            }
            
            while (left <= right && nums[right] < pivot) {
                right--;
            } 
            
            if (left <= right) {
                swap(left++, right--, nums);
            }
        }
        
        if (k <= right) {
            return quickSelect(start, right, nums, k);
        } else if (k >= left) {
            return quickSelect(left, end, nums, k);
        } else {
            return nums[k];
        }
    }
                                        
    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}