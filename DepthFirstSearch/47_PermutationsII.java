class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (nums == null) {
            return res;
        }
        
        if (nums.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        List<Integer> permutation = new ArrayList<>();
        
        helper(nums, visited, permutation, res);
        
        return res;
    }
    
    private void helper(int[] nums, 
                        boolean[] visited,
                        List<Integer> permutation,
                        List<List<Integer>> res) {
        if (permutation.size() == nums.length) {
            res.add(new ArrayList<Integer>(permutation));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1]) {
                continue;
            }
            
            if (visited[i]) {
                continue;
            }
            
            permutation.add(nums[i]);
            visited[i] = true;
            
            helper(nums, visited, permutation, res);
            
            permutation.remove(permutation.size() - 1);
            visited[i] = false;            
        }
    }
}