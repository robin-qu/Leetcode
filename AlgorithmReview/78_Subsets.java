// class Solution {
//     public List<List<Integer>> subsets(int[] nums) {
//         List<List<Integer>> res = new ArrayList<>();
        
//         if (nums == null || nums.length == 0) {
//             return res;
//         }
        
//         List<Integer> subset = new ArrayList<>();
        
//         helper(nums, 0, subset, res);
        
//         return res;
//     }
    
//     private void helper(int[] nums, int start,
//                         List<Integer> subset, 
//                         List<List<Integer>>res) {
//         res.add(new ArrayList<>(subset));
        
//         for (int i = start; i < nums.length; i++) {
//             subset.add(nums[i]);
//             helper(nums, i + 1, subset, res);
//             subset.remove(subset.size() - 1);
//         }
//     }
// }

// // Iterative
// class Solution {
//     public List<List<Integer>> subsets(int[] nums) {
//         List<List<Integer>> res = new ArrayList<>();
        
//         if (nums == null || nums.length == 0) {
//             return res;
//         }
        
//         List<Integer> subset = new ArrayList<>();
        
//         res.add(new ArrayList<>(subset));
        
//         for (int n : nums) {
//             int size = res.size();
            
//             for (int i = 0; i < size; i++) {
//                 List<Integer> set = new ArrayList<>(res.get(i));
//                 set.add(n);
//                 res.add(new ArrayList<>(set));
//             }
//         }
        
//         return res;
//     }
// }

// Bit manipulation
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        int n = nums.length;
        
        for (int i = 0; i < (1 << n); i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            res.add(new ArrayList<>(subset));
        }
        
        return res;
    }
}