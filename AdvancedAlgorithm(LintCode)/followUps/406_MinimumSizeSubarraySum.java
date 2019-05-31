// public class Solution {
//     /**
//      * @param nums: an array of integers
//      * @param s: An integer
//      * @return: an integer representing the minimum size of subarray
//      */
//     public int minimumSize(int[] nums, int s) {
//         if (nums == null || nums.length == 0) {
//             return -1;
//         }
        
//         int n = nums.length;
//         int[] prefixSum = new int[n + 1];
//         prefixSum[0] = 0;
//         for (int i = 0; i < n; i++) {
//             prefixSum[i + 1] = prefixSum[i] + nums[i];
//         }
        
//         int minSize = Integer.MAX_VALUE;
//         int left = 0;
        
//         for (int right = 0; right < n; right++) {
//             while (prefixSum[right + 1] - prefixSum[left] >= s) {
//                 minSize = Math.min(minSize, right - left + 1);
//                 left++;
//             }
//         }
        
//         return minSize == Integer.MAX_VALUE ? -1 : minSize;
//     }
// }

// Optimized by removing the prefixSum array
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
        int sum = 0;
        
        int minSize = Integer.MAX_VALUE;
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            sum += nums[right];
            while (sum >= s) {
                minSize = Math.min(minSize, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        
        return minSize == Integer.MAX_VALUE ? -1 : minSize;
    }
}
