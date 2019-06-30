// // brute force
// public class Solution {
//     /**
//      * @param nums: an array with positive and negative numbers
//      * @param k: an integer
//      * @return: the maximum average
//      */
//     public double maxAverage(int[] nums, int k) {
//         if (nums == null || nums.length == 0 || k > nums.length) {
//             return 0.0;
//         }
        
//         double max = -Double.MAX_VALUE;
//         for (int i = 0; i < nums.length; i++) {
//             double sum = 0.0;
//             for (int j = i; j < nums.length; j++) {
//                 sum += nums[j];
//                 if (j - i + 1 >= k) {
//                     max = Math.max(max, sum / (j - i + 1));
//                 }
//             }
//         }
        
//         return max;
//     }
// }


// binary search
public class Solution {
    /**
     * @param nums: an array with positive and negative numbers
     * @param k: an integer
     * @return: the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) {
            return 0.0;
        }
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int n : nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        double left = (double) min;
        double right = (double) max;
        
        while (Math.abs(left - right) > 1e-5) {
            double mid = left + (right - left) / 2;
            
            if (canGet(mid, nums, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    private boolean canGet(double avg, int[] nums, int k) {
        int n = nums.length;
        double min = 0.0;
        double leftSum = 0.0;
        double rightSum = 0.0;
        
        for (int i = 0; i < k; i++) {
            rightSum += nums[i] - avg;
        }
        
        for (int i = k; i < nums.length; i++) {
            if (rightSum - min >= 0) {
                return true;
            }
            
            rightSum += nums[i] - avg;
            leftSum += nums[i - k] - avg;
            min = Math.min(min, leftSum);
        }
        
        if (rightSum - min >= 0) {
            return true;
        }
        
        return false;
    }
}