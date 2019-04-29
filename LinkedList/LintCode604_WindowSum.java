// public class Solution {
//     /**
//      * @param nums: a list of integers.
//      * @param k: length of window.
//      * @return: the sum of the element inside the window at each moving.
//      */
//     public int[] winSum(int[] nums, int k) {
//         if (nums == null || nums.length == 0 || k <= 0) {
//             return new int[0];
//         }
        
//         int len = nums.length;
//         int[] prefixSum = new int[len + 1];
//         int[] res = new int[len - k + 1];
        
//         prefixSum[0] = 0;
//         for (int i = 0; i < len; i++) {
//             prefixSum[i + 1] = prefixSum[i] + nums[i];
//         }
        
//         for (int i = 0; i < res.length; i++) {
//             res[i] = prefixSum[i + k] - prefixSum[i];
//         }
        
//         return res;
//     }
// }

public class Solution {
    /**
     * @param nums: a list of integers.
     * @param k: length of window.
     * @return: the sum of the element inside the window at each moving.
     */
    public int[] winSum(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        
        int len = nums.length;
        int[] res = new int[len - k + 1];
        
        res[0] = 0;
        for (int i = 0; i < k; i++) {
            res[0] += nums[i];
        }
        
        for (int i = 1; i < len - k + 1; i++) {
            res[i] = res[i - 1] - nums[i - 1] + nums[i + k - 1];
        }
        
        return res;
    }
}