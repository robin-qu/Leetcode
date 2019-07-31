// O(n)time O(1)space
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int n = nums.length;
        
        int i = n - 2;  // i is the index of the first descending element from backward
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        
        if (i < 0) {
            reverse(0, n, nums);
        } else {
            int j = n - 1;  // j is the index of the last element that is larger than nums[i]
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            
            swap(i, j, nums);
            reverse(i + 1, n, nums);
        }
    }
    
    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void reverse(int i, int j, int[] nums) {
        int left = i;
        int right = j - 1;
        
        while (left < right) {
            swap(left, right, nums);
            left++;
            right--;
        }
    }
}