public class Solution {
    /**
     * @param nums: An integer array
     * @return: nothing
     */
    public void recoverRotatedSortedArray(List<Integer> nums) {
        // write your code here
        if (nums == null || nums.size() == 0) {
            throw new IllegalArgumentException();
        }
        
        int pivot = findMinIdx(nums);
        
        reverse(nums, 0, pivot - 1);
        reverse(nums, pivot, nums.size() - 1);
        reverse(nums, 0, nums.size() - 1);
        
    }
    
    // Finds the index of the minumum value in the list
    private int findMinIdx(List<Integer> nums) {
        int low = 0;
        int high = nums.size() - 1;
        int mid;
        
        while (low + 1 < high) {
            mid = low + (high - low) / 2;
            if (nums.get(mid) == nums.get(high)) {
                high--;
            } else if (nums.get(mid) < nums.get(high)) {
                high = mid;
            } else {
                low = mid;
            }
        }
        
        return nums.get(low) <= nums.get(high) ? low : high;
    }
    
    // Reverses a list
    private void reverse(List<Integer> list, int start, int end) {
        while (start < end) {
            int temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
            start++;
            end--;
        }
    }
}