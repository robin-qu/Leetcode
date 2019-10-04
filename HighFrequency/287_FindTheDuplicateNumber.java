// // BinarySearch O(nlogn)time O(1)space
// class Solution {
//     public int findDuplicate(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int n = nums.length;
        
//         int low = 1;
//         int high = n - 1;
        
//         while (low + 1 < high) {
//             int mid = low + (high - low) / 2;
//             int count = countLess(mid, nums);
            
//             if (count <= mid) {
//                 low = mid;
//             } else {
//                 high = mid;
//             }
//         }
        
//         if (countLess(low, nums) > low) {
//             return low;
//         }
        
//         return high;
//     }
    
//     private int countLess(int a, int[] nums) {
//         int count = 0;
//         for (int num : nums) {
//             if (num <= a) {
//                 count++;
//             }
//         }
        
//         return count;
//     }
// }


// Two Pointers O(n)time O(1)space
class Solution {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        
        int slow = nums[0];
        int fast = nums[0];
        
        while (nums[slow] != nums[nums[fast]]) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        
        int p = 0;
        
        while (nums[p] != nums[slow]) {
            slow = nums[slow];
            p = nums[p];
        }
        
        return nums[p];
    }
}