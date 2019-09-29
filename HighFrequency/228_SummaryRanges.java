// // O(n)time O(1)space
// class Solution {
//     public List<String> summaryRanges(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return new ArrayList<>();
//         }
        
//         int n = nums.length;
//         List<String> res = new ArrayList<>();
//         StringBuilder sb = new StringBuilder();
//         sb.append(nums[0]);
//         int start = nums[0];
        
//         for (int i = 1; i < n; i++) {
//             if (nums[i] == 1 + nums[i - 1]) {
                
//             } else {
//                 if (start != nums[i - 1]) {
//                     sb.append("->");
//                     sb.append(nums[i - 1]);
//                 }
//                 res.add(sb.toString());
//                 sb = new StringBuilder();
//                 sb.append(nums[i]);
//                 start = nums[i];
//             }
//         }
        
//         if (nums[n - 1] != start && n != 1) {
//             sb.append("->");
//             sb.append(nums[n - 1]);
//         }
//         res.add(sb.toString());
        
//         return res;
//     }
// }


// two pointers O(n)time O(1)space
class Solution {
    public List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        int n = nums.length;
        List<String> res = new ArrayList<>();
        
        int left = 0;
        while (left < n) {
            int right = left;
            while (right + 1 < n && nums[right] == nums[right + 1] - 1) {
                right++;
            }
            
            if (left == right) {
                res.add(String.valueOf(nums[right]));
            } else {
                res.add(nums[left] + "->" + nums[right]);
            }
            
            left = right + 1;
        }
        
        return res;
    }
}