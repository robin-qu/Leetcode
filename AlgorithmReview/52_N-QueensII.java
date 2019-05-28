class Solution {
    private int count;
    
    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }
        
        List<Integer> sol = new ArrayList<>();
        count = 0;
        
        dfs(n, sol);
        
        return count;
    }
    
    private void dfs(int n, List<Integer> sol) {
        if (sol.size() == n) {
            count++;
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (!canPlace(sol, i, n)) {
                continue;
            }
            sol.add(i);
            dfs(n, sol);
            sol.remove(sol.size() - 1);
        }
    }
    
    private boolean canPlace(List<Integer> sol, int row, int n) {
        int col = sol.size();
        for (int i = 0; i < sol.size(); i++) {
            if (sol.get(i) == row || i - sol.get(i) == col - row ||
                i + sol.get(i) == col + row) {
                return false;
            }
        }
        
        return true;
    }
}