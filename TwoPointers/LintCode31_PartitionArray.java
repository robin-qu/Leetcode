// public class Solution {
//     /**
//      * @param nums: The integer array you should partition
//      * @param k: An integer
//      * @return: The index after partition
//      */
//     public int partitionArray(int[] nums, int k) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int left = 0;
//         int right = nums.length - 1;
        
//         while (left < right) {
//             while (left < right && nums[left] < k) {
//                 left++;
//             }
            
//             while (left < right && nums[right] >= k) {
//                 right--;
//             }
            
//             if (left < right) {
//                 swap(nums, left, right);
//                 left++;
//                 right--;
//             }
//         }
//         // post: left = right or left = right + 1
        
//         if (nums[left] >= k) {
//             return left;
//         }
        
//         return left + 1;
//     }
    
//     private void swap(int[] nums, int i, int j) {
//         int temp = nums[i];
//         nums[i] = nums[j];
//         nums[j] = temp;
//     }
// }
public class Solution {
    /**
     * @param nums: The integer array you should partition
     * @param k: An integer
     * @return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            while (left <= right && nums[left] < k) {
                left++;
            }
            
            while (left <= right && nums[right] >= k) {
                right--;
            }
            
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        // post: left = right + 1 or left = right + 2
        if (left - 1 >= 0 && nums[left - 1] >= k) {
            return left - 1;
        }
        
        return left;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}