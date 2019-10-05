// Two pointers O(n)time O(1)space
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        
        int res = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            sum += nums[right];
            while (sum >= s && left <= right) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left++];
            }
        }
        
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}