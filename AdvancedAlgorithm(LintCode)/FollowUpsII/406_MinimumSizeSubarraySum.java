public class Solution {
    /**
     * @param nums: an array of integers
     * @param s: An integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int n = nums.length;
        int res = n + 1;
        
        int left = 0;
        int sum = 0;
        for (int right = 0; right < n; right++) {
            sum += nums[right];
            
            while (sum >= s && left <= right) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        
        return res == n + 1 ? -1 : res;
    }
}
