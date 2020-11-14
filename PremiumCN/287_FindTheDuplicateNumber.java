// class Solution {
//     public int findDuplicate(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int n = nums.length;
//         int left = 1;
//         int right = n - 1;
//         while (left + 1 < right) {
//             int mid = left + (right - left) / 2;
//             int count = getCount(mid, nums);
//             if (mid < count) {
//                 right = mid;
//             } else {
//                 left = mid;
//             }
//         } 
        
//         if (getCount(left, nums) > left) {
//             return left;
//         }
        
//         return right;
//     }
                                        
//     private int getCount(int num, int[] nums) {
//         int count = 0;
//         for (int n : nums) {
//             if (n <= num) {
//                 count++;
//             }
//         }
//         return count;
//     }
// }


class Solution {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int slow = 0;
        int fast = 0;
        
        while (nums[slow] != nums[nums[fast]]) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        
        
        int curr = 0;
        
        while (curr != nums[slow]) {
            curr = nums[curr];
            slow = nums[slow];
        }
        
        return curr;
    }
}