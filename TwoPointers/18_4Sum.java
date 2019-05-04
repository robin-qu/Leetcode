// // Hash map
// class Solution {
//     public List<List<Integer>> fourSum(int[] nums, int target) {
//         List<List<Integer>> res = new ArrayList<>();
//         if (nums == null || nums.length < 4) {
//             return res;
//         }
        
//         Arrays.sort(nums);
//         Map<Integer, List<List<Integer>>> map = new HashMap<>();
//         int len = nums.length;
        
//         for (int i = 2; i < len; i++) {
//             for (int j = i + 1; j < len; j++) {
//                 map.putIfAbsent(nums[i] + nums[j], new ArrayList<>());
//                 List<Integer> list = new ArrayList<>();
//                 list.add(i);
//                 list.add(j);
//                 map.get(nums[i] + nums[j]).add(list);
//             }
//         }
        
//         for (int i = 0; i < len - 2; i++) {
//             if (i > 0 && nums[i] == nums[i - 1]) {
//                 continue;
//             }
//             for (int j = i + 1; j < len - 2; j++) {
//                 if (j > i + 1 && nums[j] == nums[j - 1]) {
//                     continue;
//                 }
//                 if (map.containsKey(target - nums[i] - nums[j])) {
//                     List<List<Integer>> listList = map.get(target - nums[i] - nums[j]);
//                     int prev1 = Integer.MAX_VALUE;
//                     int prev2 = Integer.MAX_VALUE;
//                     for (int k = 0; k < listList.size(); k++) {
//                         List<Integer> twoSum = listList.get(k);
//                         if (twoSum.get(0) <= j || // i < j < get(0) < get(1)
//                             nums[twoSum.get(0)] == prev1 || //remove duplicates
//                             nums[twoSum.get(1)] == prev2) {
//                             continue;
//                         }
//                         List<Integer> fourSum = new ArrayList<>();
//                         fourSum.add(nums[i]);
//                         fourSum.add(nums[j]);
//                         fourSum.add(nums[twoSum.get(0)]);
//                         fourSum.add(nums[twoSum.get(1)]);
//                         res.add(fourSum);
//                         prev1 = nums[twoSum.get(0)];
//                         prev2 = nums[twoSum.get(1)];
//                     }
//                 }
//             }
//         }
        
//         return res;
//     }
// }


// Two pointers
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }
        
        Arrays.sort(nums);
        int len = nums.length;
        
        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                
                int currTarget = target - nums[i] - nums[j];
                int left = j + 1;
                int right = len - 1;
                
                while (left < right) {
                    if (nums[left] + nums[right] < currTarget) {
                        left++;
                    } else if (nums[left] + nums[right] > currTarget) {
                        right--;
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        res.add(list);
                        left++;
                        right--;
                        while (left < right && nums[left - 1] == nums[left]) {
                            left++;
                        }
                        while (left < right && nums[right + 1] == nums[right]) {
                            right--;
                        }
                    }
                }
            }
        }
        return res;
    }
}