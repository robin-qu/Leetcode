// Two Pointers O(n)time O(1)space
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        
        int left = 1;
        for (int right = 1; right < n; right++) {
            if (nums[right] != nums[right - 1]) {
                nums[left++] = nums[right];
            }
        }
        
        return left;
    }
}