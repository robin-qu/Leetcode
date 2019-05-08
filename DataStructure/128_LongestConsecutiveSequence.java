// class Solution {
//     public int longestConsecutive(int[] nums) {
//         Set<Integer> set = new HashSet<>();
//         for (int num : nums) {
//             set.add(num);
//         }
        
//         int res = 0;
//         for (int i = 0; i < nums.length; i++) {
//             int len = 1;
//             int down = nums[i] - 1;
//             while (set.contains(down)) {
//                 len++;
//                 set.remove(down);
//                 down--;
//             }
            
//             int up = nums[i] + 1;
//             while (set.contains(up)) {
//                 len++;
//                 set.remove(up);
//                 up++;
//             }
            
//             res = Math.max(res, len);
//         }
//         return res;
//     }
// }


class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i] - 1)) {
                int len = 1;
                int num = nums[i];
                while (set.contains(num + 1)) {
                    len++;
                    num++;
                }
                res = Math.max(res, len);
            }            
        }
        return res;
    }
}