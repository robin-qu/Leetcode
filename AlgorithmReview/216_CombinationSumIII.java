class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();
        
        helper(k, n, 1, comb, res);
        
        return res;
    }
    
    private void helper(int k, int n, int start, List<Integer> comb,
                        List<List<Integer>> res) {
        if (k < 0 || n < 0) {
            return;
        }
        
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(comb));
            return;
        }
        
        for (int i = start; i <= 9; i++) {
            comb.add(i);
            helper(k - 1, n - i, i + 1, comb, res);
            comb.remove(comb.size() - 1);
        }
    }
}