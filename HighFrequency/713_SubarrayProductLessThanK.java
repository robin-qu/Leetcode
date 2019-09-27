// O(n)time O(1)space
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int res = 0;
        int product = 1;
        int left = 0;
        for (int right = 0; right < n; right++) {
            product *= nums[right];
            while (left <= right && product >= k) {
                product /= nums[left++];
            }
            
            res += (right - left + 1);
        }
        
        return res;
    }
}