// class Solution {
//     public int searchInsert(int[] nums, int target) {
//         int left = 0;
//         int right = nums.length - 1;
//         while (left <= right) {
//             int mid = (left + right) / 2;
//             if (nums[mid] == target) return mid;
//             else if (target < nums[mid]) right = mid - 1;
//             else left = mid + 1;
//         }
//         return left;
//     }
// }

class Solution {
    public int searchInsert(int[] nums, int target) {         
        int low = 0;
        int high = nums.length - 1;
        
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (target < nums[mid]) {
                high = mid;
            } else {
                low = mid;
            }
        }
        
        if (nums[low] >= target) {
            return low;
        } else if (nums[high] >= target) {
            return high;
        }
        return high + 1;
    }
}