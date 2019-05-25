class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        
        Arrays.sort(candidates);
        
        List<Integer> comb = new ArrayList<>();
        
        helper(candidates, 0, target, comb, res);
        
        return res;
    }
    
    private void helper(int[] candidates, int start, int target,
                        List<Integer> comb, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        
        if (target == 0) {
            res.add(new ArrayList<>(comb));
        }
        
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            
            comb.add(candidates[i]);
            helper(candidates, i + 1, target - candidates[i], comb, res);
            comb.remove(comb.size() - 1);
        }
    }
}