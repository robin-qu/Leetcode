class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        ArrayList<Integer> perm = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        
        helper(nums, visited, perm, res);
        
        return res;
    }
    
    private void helper(int[] nums, boolean[] visited, 
                        List<Integer> perm, List<List<Integer>> res) {
        if (perm.size() == nums.length) {
            res.add(new ArrayList<>(perm));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            
            perm.add(nums[i]);
            visited[i] = true;
            helper(nums, visited, perm, res);
            perm.remove(perm.size() - 1);
            visited[i] = false;
        }
    }
}
