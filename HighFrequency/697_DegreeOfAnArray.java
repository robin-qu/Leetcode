// // two pointers O(n)time O(n)space  two passes
// class Solution {
//     public int findShortestSubArray(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
        
//         int n = nums.length;
        
//         Map<Integer, Integer> counts = new HashMap<>();
        
//         int degree = 0;
//         for (int num : nums) {
//             counts.put(num, counts.getOrDefault(num, 0) + 1);
//             degree = Math.max(degree, counts.get(num));
//         }
        
//         Map<Integer, Integer> map = new HashMap<>();
//         int res = Integer.MAX_VALUE;
//         int left = 0;
//         for (int right = 0; right < n; right++) {
//             int curr = nums[right];
//             map.put(curr, map.getOrDefault(curr, 0) + 1);
            
//             while (left <= right && map.get(curr) == degree) {
//                 res = Math.min(res, right - left + 1);
//                 map.put(nums[left], map.get(nums[left]) - 1);
//                 left++;
//             }
//         }
        
//         return res == Integer.MAX_VALUE ? 1 : res;
//     }
// }


// two pointers O(n)time O(n)space  one passes
class Solution {
    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        
        Map<Integer, Integer> counts = new HashMap<>();
        Map<Integer, Integer> first = new HashMap<>();
        
        int degree = 0;
        int res = 0;
        
        for (int i = 0; i < n; i++) {
            if (!first.containsKey(nums[i])) {
                first.put(nums[i], i);
            }
            counts.put(nums[i], counts.getOrDefault(nums[i], 0) + 1);
            if (counts.get(nums[i]) > degree) {
                degree = counts.get(nums[i]);
                res = i - first.get(nums[i]) + 1;
            } else if (counts.get(nums[i]) == degree) {
                res = Math.min(res, i - first.get(nums[i]) + 1);
            }
        }
        
        return res;
    }
}