public class Solution {
    /**
     * @param candidates: A list of integers
     * @param target: An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }
        
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        
        dfs(candidates, target, 0, new ArrayList<>(), res);
        
        return res;
    }
    
    private void dfs(int[] candidates, int target, int idx, List<Integer> list, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = idx; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1]) {
                continue;
            }
            list.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, list, res);
            list.remove(list.size() - 1);
        }
    }
}