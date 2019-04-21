class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null) {
            return res;
        }
        
        if (candidates.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        
        Arrays.sort(candidates);
        
        List<Integer> combination = new ArrayList<>();
        helper(candidates, 0, target, combination, res);
        
        return res;
    }
    
    private void helper(int[] candidates, 
                        int idx,
                        int target,
                        List<Integer> combination,
                        List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        
        if (target == 0) {
            res.add(new ArrayList<>(combination));
            return;
        }
        
        for (int i = idx; i < candidates.length; i++) {
            combination.add(candidates[i]);
            helper(candidates, i, target - candidates[i], combination, res);
            combination.remove(combination.size() - 1);
        }
    }
}