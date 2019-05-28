class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        
        if (n <= 0) {
            return res;
        }
        
        List<Integer> sol = new ArrayList<>();
        
        dfs(n, sol, res);
        
        return res;
    }
    
    private void dfs(int n, List<Integer> sol, List<List<String>> res) {
        if (sol.size() == n) {
            addSolution(sol, res);
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (!canPlace(sol, i, n)) {
                continue;
            }
            sol.add(i);
            dfs(n, sol, res);
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
    
    private void addSolution(List<Integer> sol, List<List<String>> res) {
        int n = sol.size();
        List<String> chessboard = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (j == sol.get(i)) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            chessboard.add(sb.toString());
        }
        res.add(chessboard);
    }
}