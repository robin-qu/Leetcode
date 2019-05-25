// class Solution {
//     public List<List<Integer>> subsetsWithDup(int[] nums) {
//         List<List<Integer>> res = new ArrayList<>();
        
//         if (nums == null || nums.length == 0) {
//             return res;
//         }
        
//         Arrays.sort(nums);
        
//         List<Integer> subset = new ArrayList<>();
        
//         helper(nums, 0, subset, res);
        
//         return res;
//     }
    
//     private void helper(int[] nums, 
//                         int start, 
//                         List<Integer> subset, 
//                         List<List<Integer>> res) {
//         res.add(new ArrayList<>(subset));
        
//         for (int i = start; i < nums.length; i++) {
//             subset.add(nums[i]);
//             helper(nums, i + 1, subset, res);
//             subset.remove(subset.size() - 1);
//             while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
//                 i++;
//             }
//         }
//     }
// }


class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        Arrays.sort(nums);
        
        List<Integer> subset = new ArrayList<>();
        
        helper(nums, 0, subset, res);
        
        return res;
    }
    
    private void helper(int[] nums, 
                        int start, 
                        List<Integer> subset, 
                        List<List<Integer>> res) {
        res.add(new ArrayList<>(subset));
        
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            subset.add(nums[i]);
            helper(nums, i + 1, subset, res);
            subset.remove(subset.size() - 1);
        }
    }
}