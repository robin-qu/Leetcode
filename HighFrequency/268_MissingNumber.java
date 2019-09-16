// // O(n)time O(1)space
// class Solution {
//     public int missingNumber(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int n = nums.length;
        
//         for (int i = 0; i < n; i++) {
//             int idx = nums[i] >= 0 ? nums[i] : Math.abs(nums[i]) - 1;
            
//             if (idx < n) {
//                 nums[idx] = -nums[idx] - 1;
//             }
//         }
        
//         for (int i = 0; i < n; i++) {
//             if (nums[i] >= 0) {
//                 return i;
//             }
//         }
        
//         return n;
//     }
// }


// bit O(n)time O(1)space
class Solution {
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = res ^ i ^ nums[i];
        }
        
        return res ^ n;
    }
}