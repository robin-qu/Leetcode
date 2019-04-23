class Solution {
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        
        List<List<String>> solutions = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        
        helper(n, solution, solutions);
        
        return solutions;
    }
    
    // Backtracking
    private void helper(int n, 
                        List<Integer> solution, 
                        List<List<String>> solutions) {
        if (solution.size() == n) {
            solutions.add(drawChessboard(solution));
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (isValid(i, solution)) {
                solution.add(i);
                helper(n, solution, solutions);
                solution.remove(solution.size() - 1);
            }
        }
    }
    
    // Check whether puting a queen at the position is valid
    private boolean isValid(int position, List<Integer> solution) {
        int newCol = solution.size();
        for (int col = 0; col < solution.size(); col++) {
            // Same row
            if (position == solution.get(col)) {
                return false;
            }
            
            // topleft-bottomright diagonal
            if (newCol - position == col - solution.get(col)) {
                return false;
            }
            
            // topright-bottomleft diagonal
            if (newCol + position == col + solution.get(col)) {
                return false;
            }
        }
        return true;
    }
    
    private List<String> drawChessboard(List<Integer> solution) {
        List<String> chessboard = new ArrayList<>();
        for (int i = 0; i < solution.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < solution.size(); j++) {
                if (solution.get(i) == j) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            chessboard.add(sb.toString());
        }
        return chessboard;
    }
}