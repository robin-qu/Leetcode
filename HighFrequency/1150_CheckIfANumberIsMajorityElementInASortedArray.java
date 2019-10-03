// // O(logn)time O(1)space
// class Solution {
//     public boolean isMajorityElement(int[] nums, int target) {
//         if (nums == null || nums.length == 0) {
//             return false;
//         }
        
//         int n = nums.length;
        
//         int begin = findFirst(nums, target);
//         int end = findLast(nums, target);
        
//         return end - begin + 1 > n / 2;
//     }
    
//     private int findFirst(int[] nums, int target) {
//         int left = 0;
//         int right = nums.length - 1;
        
//         while (left + 1 < right) {
//             int mid = left + (right - left) / 2;
            
//             if (nums[mid] < target) {
//                 left = mid;
//             } else {
//                 right = mid;
//             }
//         }
        
//         if (nums[left] == target) {
//             return left;
//         }
        
//         if (nums[right] == target) {
//             return right;
//         }
        
//         return 0;
//     }
    
//     private int findLast(int[] nums, int target) {
//         int left = 0;
//         int right = nums.length - 1;
        
//         while (left + 1 < right) {
//             int mid = left + (right - left) / 2;
            
//             if (nums[mid] > target) {
//                 right = mid;
//             } else {
//                 left = mid;
//             }
//         }
        
//         if (nums[right] == target) {
//             return right;
//         }
        
//         if (nums[left] == target) {
//             return left;
//         }
        
//         return Integer.MAX_VALUE;
//     }
// }


// O(1)time O(1)space
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int n = nums.length;
        
        return n % 2 == 0 ? (nums[n / 2 - 1] == target && nums[n / 2] == target) : (nums[n / 2] == target);
    }
}