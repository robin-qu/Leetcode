// Backtracking O(2^n)worsttime
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }
        
        List<List<Integer>> res = new ArrayList<>();
        
        dfs(0, target, candidates, new ArrayList<>(), res);
        
        return res;
    }
    
    private void dfs(int idx, int target, int[] candidates, List<Integer> list, List<List<Integer>> res) {
        if (target == 0) {
            res.add (new ArrayList<>(list));
            return;
        }
        
        if (target < 0) {
            return;
        }
        
        for (int i = idx; i < candidates.length; i++) {
            list.add(candidates[i]);
            dfs(i, target - candidates[i], candidates, list, res);
            list.remove(list.size() - 1);
        }
    }
}