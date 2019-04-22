class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n <= 0) {
            return res;
        }
        
        List<Integer> combination = new ArrayList<>();
        helper(1, k, n, combination, res);
        
        return res;
    }
    
    private void helper(int num,
                        int k,
                        int n,
                        List<Integer> combination,
                        List<List<Integer>> res) {
        if (k < 0 || n < 0) {
            return;
        }
        
        if (k == 0 && n == 0) {
            res.add(new ArrayList<Integer>(combination));
        }
        
        for (int i = num; i <= 9; i++) {
            combination.add(i);
            helper(i + 1, k - 1, n - i, combination, res);
            combination.remove(combination.size() - 1);
        }
    }
}