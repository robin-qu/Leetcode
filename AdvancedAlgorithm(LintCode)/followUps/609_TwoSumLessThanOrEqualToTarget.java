// public class Solution {
//     /**
//      * @param nums: an array of integer
//      * @param target: an integer
//      * @return: an integer
//      */
//     public int twoSum5(int[] nums, int target) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         Arrays.sort(nums);
        
//         int n = nums.length;
//         int res = 0;
        
//         for (int left = 0; left < n - 1; left++) {
//             int right = left + 1;
//             while (right < n && nums[left] + nums[right] <= target) {
//                 right++;
//             }
//             res += right - left - 1;
//         }
        
//         return res;
//     }
// }


public class Solution {
    /**
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
     */
    public int twoSum5(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Arrays.sort(nums);
        
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int res = 0;
        
        while (left < right) {
            if (nums[left] + nums[right] <= target) {
                res += right - left;
                left++;
            } else {
                right--;
            }
        }
        
        return res;
    }
}