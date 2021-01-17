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
        if (idx == candidates.length || target <= 0) {
            if (target == 0) {
                res.add(new ArrayList<>(list));
            }
            return;
        }
        
        list.add(candidates[idx]);
        dfs(idx, target - candidates[idx], candidates, list, res);
        list.remove(list.size() -  1);

        dfs(idx + 1, target, candidates, list, res);
    }
}