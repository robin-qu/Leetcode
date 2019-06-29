// // binary search
// public class Solution {
//     /**
//      * @param nums: an array containing n + 1 integers which is between 1 and n
//      * @return: the duplicate one
//      */
//     public int findDuplicate(int[] nums) {
//         if (nums == null || nums.length <= 1) {
//             return -1;
//         }
        
//         int left = 1;
//         int right = nums.length - 1;
        
//         while (left + 1 < right) {
//             int mid = left + (right - left) / 2;
            
//             if (count(nums, mid) <= mid) {
//                 left = mid;
//             } else {
//                 right = mid;
//             }
//         }
    
//         return count(nums, left) > left ? left : right;
//     }
    
//     private int count(int[] nums, int num) {
//         int res = 0;
        
//         for (int n : nums) {
//             if (n <= num) {
//                 res++;
//             }
//         }
        
//         return res;
//     }
// }

// binary search
public class Solution {
    /**
     * @param nums: an array containing n + 1 integers which is between 1 and n
     * @return: the duplicate one
     */
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return -1;
        }
        
        int slow = nums[0];
        int fast = nums[0];
        
        while (nums[slow] != nums[nums[fast]]) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        
        int pointer = 0;
        
        while (pointer != slow) {
            pointer = nums[pointer];
            slow = nums[slow];
        }
        
        return pointer;  
    }
}