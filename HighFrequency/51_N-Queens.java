// dfs O(n!)time O(n)space
class Solution {    
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        
        List<List<Integer>> sols = new ArrayList<>();
        
        dfs(0, n, new ArrayList<>(), sols);
        
        return getRes(sols, n);
    }
    
    private void dfs(int col, int n, List<Integer> sol, List<List<Integer>> sols) {
        if (col == n) {
            sols.add(new ArrayList<>(sol));
            return;
        }
        
        for (int row = 0; row < n; row++) {            
            boolean canPlace = true;
            for (int col1 = 0; col1 < sol.size(); col1++) {
                int row1 = sol.get(col1);
                canPlace = canPlace && !canAttack(row, col, row1, col1);
            }
            
            if (canPlace) {
                sol.add(row);
                dfs(col + 1, n, sol, sols);
                sol.remove(sol.size() - 1);
            }
        }
    }
    
    private boolean canAttack(int row1, int col1, int row2, int col2) {
        return (row1 == row2) || (col1 == col2) || (row1 + col1 == row2 + col2) || (col1 - row1 == col2 - row2);
    }
       
    private List<List<String>> getRes(List<List<Integer>> sols, int n) {
        List<List<String>> res = new ArrayList<>();
        
        for (List<Integer> list : sols) {
            List<String> strList = new ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb.append(j == list.get(i) ? 'Q' : '.');
                }
                strList.add(sb.toString());
            }
            
            res.add(strList);
        }
        
        return res;
    }
}