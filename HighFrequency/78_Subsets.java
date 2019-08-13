class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        List<List<Integer>> res = new ArrayList<>();
        
        dfs(0, nums, new ArrayList<>(), res);
        
        return res;
    }
    
    private void dfs(int idx, int[] nums, List<Integer> list, List<List<Integer>> res) {
        res.add(new ArrayList<>(list));
        
        for (int i = idx; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(i + 1, nums, list, res);
            list.remove(list.size() - 1);
        }
    }
}