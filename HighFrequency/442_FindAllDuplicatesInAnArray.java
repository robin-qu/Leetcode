// // O(n)time O(1)space  initial version
// class Solution {
//     public List<Integer> findDuplicates(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return new ArrayList<>();
//         }
        
//         int n = nums.length;
//         List<Integer> res = new ArrayList<>();
        
//         int i = 0;
//         while (i < n) {
//             if (nums[i] != i + 1) {
//                 if (nums[i] != nums[nums[i] - 1]) {
//                     swap(i, nums[i] - 1, nums);
//                     continue;
//                 } else if (!res.contains(nums[i])) {
//                     res.add(nums[i]);
//                 }
//             }
            
//             i++;
//         }
        
//         return res;
//     }
    
//     private void swap(int i, int j, int[] nums) {
//         int temp = nums[i];
//         nums[i] = nums[j];
//         nums[j] = temp;
//     }
// }


// O(n)time O(1)space  initial version
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            int idx = Math.abs(nums[i]) - 1;
            if (nums[idx] < 0) {
                res.add(idx + 1);
            } else {
                nums[idx] = -nums[idx];  // idx + 1 appears
            }
        }
        
        return res;
    }
    
    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}