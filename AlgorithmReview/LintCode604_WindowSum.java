public class Solution {
    /**
     * @param nums: a list of integers.
     * @param k: length of window.
     * @return: the sum of the element inside the window at each moving.
     */
    public int[] winSum(int[] nums, int k) {
        if (nums == null || nums.length <= 0 || k <= 0 || k > nums.length) {
            return new int[0];
        }
        
        int[] res = new int[nums.length - k + 1];
        
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        res[0] = sum;
        
        for (int i = k; i < nums.length; i++) {
            sum = sum + nums[i] - nums[i - k];
            res[i - k + 1] = sum;
        }
        
        return res;
    }
}