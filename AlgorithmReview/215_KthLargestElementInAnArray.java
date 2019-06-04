class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) {
            return 0;
        }
        
        return quickSelect(k, nums, 0, nums.length - 1);
    }
    
    private int quickSelect(int k, int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        
        int left = start;
        int right = end;
        int pivot = nums[start + (end - start) / 2];
        
        while (left <= right) {
            while (left <= right && nums[left] > pivot) {
                left++;
            }
            
            while (left <= right && nums[right] < pivot) {
                right--;
            }
            
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        
        // start ... right  k - 1  left ... end
        if (k - 1 <= right) {
            return quickSelect(k, nums, start, right);
        } else if (left <= k - 1) {
            return quickSelect(k, nums, left, end);
        } else {
            return nums[k - 1];
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}