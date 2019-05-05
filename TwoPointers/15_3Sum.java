// // Two pointers
// class Solution {
//     public List<List<Integer>> threeSum(int[] nums) {
//         List<List<Integer>> res = new ArrayList<>();
//         if (nums == null || nums.length == 0) {
//             return res;
//         }
        
//         Arrays.sort(nums);
        
//         for (int i = 0; i < nums.length - 2; i++) {
//             // Remove duplicates
//             if (i > 0 && nums[i] == nums[i - 1]) {
//                 continue;
//             }
//             twoSum(nums, i + 1, 0 - nums[i], res);
//         }
        
//         return res;
//     }
    
//     private void twoSum(int[] nums, 
//                         int idx, 
//                         int target, 
//                         List<List<Integer>> res) {
//         int left = idx;
//         int right = nums.length - 1;
        
//         while (left < right) {
//             if (nums[left] + nums[right] < target) {
//                 left++;
//             } else if (nums[left] + nums[right] > target) {
//                 right--;
//             } else {
//                 List<Integer> list = new ArrayList<>();
//                 list.add(-target);
//                 list.add(nums[left]);
//                 list.add(nums[right]);
//                 res.add(list);
                
//                 left++;
//                 right--;
//                 // Remove duplicates
//                 while (left < right && nums[left] == nums[left - 1]) {
//                     left++;
//                 }
//                 // Remove duplicates
//                 while (left < right && nums[right] == nums[right + 1]) {
//                     right--;
//                 }
//             }
//         }
//     }
// }


// HashMap
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        
        Arrays.sort(nums);
        
        int len = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < len; i++) {
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
        
        for (int i = 0; i < len; i++) {
            // remove duplicates
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            for (int j = i + 1; j < len; j++) {
                // remove duplicates
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }
                
                if (map.containsKey(0 - nums[i] - nums[j])) {
                    List<Integer> indices = map.get(0 - nums[i] - nums[j]);
                    int prev = Integer.MAX_VALUE;
                    for (int idx : indices) {
                        if (prev == nums[idx] ||  // remove duplicates
                            idx <= j) {  // i j idx
                            continue;
                        }
                        List<Integer> threeSum = new ArrayList<>();
                        threeSum.add(nums[i]);
                        threeSum.add(nums[j]);
                        threeSum.add(nums[idx]);
                        res.add(threeSum);
                        prev = nums[idx];
                    }
                }
            }
        }
        
        return res;
    }
}