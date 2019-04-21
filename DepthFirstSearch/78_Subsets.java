class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (nums == null) {
            return res;
        }
            
        if (nums.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        
        List<Integer> subset = new ArrayList<>();
        helper(nums, 0, subset, res);
        
        return res;
    }
    
    private void helper(int[] nums,
                        int idx,
                        List<Integer> subset, 
                        List<List<Integer>> res) {        
        res.add(new ArrayList<Integer>(subset));
        
        for (int i = idx; i < nums.length; i++) {
            subset.add(nums[i]);
            helper(nums, i + 1, subset, res);
            subset.remove(subset.size() - 1);
        }
    }
}