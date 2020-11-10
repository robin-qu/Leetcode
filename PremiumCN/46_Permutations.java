class Solution {
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, nums, res);
        return res;
    }
                                        
    private void dfs(int idx, int[] nums, List<List<Integer>> res) {
        if (idx == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(list);
            return;
        }
        
        for (int i = idx; i < nums.length; i++) {
            swap(idx, i, nums);
            dfs(idx + 1, nums, res);
            swap(idx, i, nums);
        }
    }
                                        
    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}