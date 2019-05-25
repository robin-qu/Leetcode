class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        
        List<Integer> comb = new ArrayList<>();
        
        helper(candidates, 0, target, comb, res);
        
        return res;
    }
    
    private void helper(int[] candidates, int start, 
                        int target, List<Integer> comb,
                        List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        
        if (target == 0) {
            res.add(new ArrayList<>(comb));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            comb.add(candidates[i]);
            helper(candidates, i, target - candidates[i], comb, res);
            comb.remove(comb.size() - 1);
        }
    }
}