public class Solution {
    /**
     * @param num: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        if (num == null || num.length == 0) {
            return new ArrayList<>();
        }
        
        Arrays.sort(num);
        
        List<List<Integer>> res = new ArrayList<>();
        
        dfs(num, target, 0, new ArrayList<>(), res);
        
        return res;
    }
    
    private void dfs(int[] num, int target, int idx, List<Integer> list, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = idx; i < num.length; i++) {
            if (i > idx && num[i] == num[i - 1]) {
                continue;
            }
            list.add(num[i]);
            dfs(num, target - num[i], i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }
}