// class Solution {
//     public int removeDuplicates(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         if (nums.length == 1) {
//             return 1;
//         }
        
//         int left = 0;
        
//         for (int right = 1; right < nums.length; right++) {
//             if (nums[right] != nums[left]) {
//                 left++;
//                 nums[left] = nums[right];
//             } else if (left == 0 || nums[right] != nums[left - 1]) {
//                 left++;
//                 nums[left] = nums[right];
//             }
//         }
        
//         return left + 1;
//     }
// }

class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        
        int idx = 0;
        
        for (int num : nums) {
            if (idx == 0 || idx == 1 || num != nums[idx - 2]) {
                nums[idx] = num;
                idx++;
            }
        }
        
        return idx;
    }
}