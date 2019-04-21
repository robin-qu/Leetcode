// class Solution {
//     public List<List<Integer>> subsetsWithDup(int[] nums) {
//         List<List<Integer>> res = new ArrayList<>();
        
//         if (nums == null) {
//             return res;
//         }
            
//         if (nums.length == 0) {
//             res.add(new ArrayList<>());
//             return res;
//         }
        
//         List<Integer> subset = new ArrayList<>();
//         Arrays.sort(nums);
//         helper(nums, 0, subset, res);
        
//         return res;
//     }
    
//     private void helper(int[] nums,
//                         int idx,
//                         List<Integer> subset, 
//                         List<List<Integer>> res) {        
//         res.add(new ArrayList<Integer>(subset));
        
//         for (int i = idx; i < nums.length; i++) {
//             subset.add(nums[i]);
//             helper(nums, i + 1, subset, res);
//             int temp = subset.remove(subset.size() - 1);
//             while (i + 1 < nums.length && nums[i + 1] == temp) {
//                 i++;
//             }
//         }
//     }
// }


class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (nums == null) {
            return res;
        }
            
        if (nums.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        
        List<Integer> subset = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, 0, subset, res);
        
        return res;
    }
    
    private void helper(int[] nums,
                        int idx,
                        List<Integer> subset, 
                        List<List<Integer>> res) {        
        res.add(new ArrayList<Integer>(subset));
        
        for (int i = idx; i < nums.length; i++) {
            if (i > idx && nums[i - 1] == nums[i]) {
                continue;
            }
            subset.add(nums[i]);
            helper(nums, i + 1, subset, res);
            subset.remove(subset.size() - 1);
        }
    }
}