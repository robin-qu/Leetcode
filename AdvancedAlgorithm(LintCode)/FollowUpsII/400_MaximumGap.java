// // Bucket sort
// public class Solution {
//     /**
//      * @param nums: an array of integers
//      * @return: the maximun difference
//      */
//     public int maximumGap(int[] nums) {
//         if (nums == null || nums.length < 2) {
//             return 0;
//         }
        
//         int n = nums.length;
        
//         int max = nums[0];
//         int min = nums[0];
//         for (int num : nums) {
//             max = Math.max(max, num);
//             min = Math.min(min, num);
//         }
        
//         if (min == max) {
//             return 0;
//         }
        
//         int avgGap = (int) Math.ceil((double) (max - min) / (n - 1));
//         int[] mins = new int[n];
//         Arrays.fill(mins, Integer.MAX_VALUE);
//         int[] maxs = new int[n];
//         Arrays.fill(maxs, Integer.MIN_VALUE);
        
//         for (int i = 0; i < n; i++) {
//             int idx = (nums[i] - min) / avgGap;
//             mins[idx] = Math.min(mins[idx], nums[i]);
//             maxs[idx] = Math.max(maxs[idx], nums[i]);
//         }
        
//         int res = 0;
//         int lastMax = min;
//         for (int i = 0; i < n; i++) {
//             if (mins[i] == Integer.MAX_VALUE || maxs[i] == Integer.MIN_VALUE) {
//                 continue;
//             }
            
//             res = Math.max(res, mins[i] - lastMax);
//             lastMax = maxs[i];
//         }
        
//         return Math.max(res, max - lastMax);
//     }
// }


// Radix sort
// animation: https://www.cs.usfca.edu/~galles/visualization/RadixSort.html
public class Solution {
    /**
     * @param nums: an array of integers
     * @return: the maximun difference
     */
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        
        radixSort(nums);
        // Arrays.sort(nums);
        
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            res = Math.max(res, nums[i] - nums[i - 1]);
        }
        
        return res;
    }
    
    private void radixSort(int[] nums) {
        int n = nums.length;
        int exp = 1;  // 1, 10, 100, ...
        int D = 10;  // 10 digits
        int[] digits = new int[D];  // 0 ... 9
        int[] A = new int[n];
        
        int max = nums[0];
        for (int num : nums) {
            max = Math.max(max, num);
        }
        
        while (max / exp > 0) {
            Arrays.fill(digits, 0);
            
            for (int i = 0; i < n; i++) {
                digits[(nums[i] / exp) % D]++;
            }
            
            for (int i = 1; i < D; i++) {
                digits[i] += digits[i - 1];
            }
            
            for (int i = n - 1; i >= 0; i--) {
                A[--digits[(nums[i] / exp) % D]] = nums[i];
            }
            
            for (int i = 0; i < n; i++) {
                nums[i] = A[i];
            }
            
            exp *= 10;
        }
    }
}