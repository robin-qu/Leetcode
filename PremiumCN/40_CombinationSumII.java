class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }

        Arrays.sort(candidates);
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

        for (int i = idx; i < candidates.length; i++) {
            if (i > idx && candidates[i] == candidates[i - 1]) {
                continue;
            }
            list.add(candidates[i]);
            dfs(i + 1, target - candidates[i], candidates, list, res);
            list.remove(list.size() - 1);
        }
    }
}