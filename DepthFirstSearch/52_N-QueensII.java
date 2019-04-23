// class Solution {
//     private int count;
    
//     public int totalNQueens(int n) {
//         if (n <= 0) {
//             return 0;
//         }
        
//         List<Integer> solution = new ArrayList<>();
        
//         helper(n, solution);
        
//         return count;
//     }
    
//     // Backtracking
//     private void helper(int n, List<Integer> solution) {
//         if (solution.size() == n) {
//             count++;
//             return;
//         }
        
//         for (int i = 0; i < n; i++) {
//             if (isValid(i, solution)) {
//                 solution.add(i);
//                 helper(n, solution);
//                 solution.remove(solution.size() - 1);
//             }
//         }
//     }
    
//     // Check whether puting a queen at the position is valid
//     private boolean isValid(int position, List<Integer> solution) {
//         int newCol = solution.size();
//         for (int col = 0; col < solution.size(); col++) {
//             // Same row
//             if (position == solution.get(col)) {
//                 return false;
//             }
            
//             // topleft-bottomright diagonal
//             if (newCol - position == col - solution.get(col)) {
//                 return false;
//             }
            
//             // topright-bottomleft diagonal
//             if (newCol + position == col + solution.get(col)) {
//                 return false;
//             }
//         }
//         return true;
//     }
// }

public class Solution {
    public static int sum;

    public int totalNQueens(int n) {
        sum = 0;
        int[] usedColumns = new int[n];
        placeQueen(usedColumns, 0);
        return sum;
    }
    
    public void placeQueen(int[] usedColumns, int row) {
        int n = usedColumns.length;
        
        if (row == n) {
            sum ++;
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (isValid(usedColumns, row, i)) {
                usedColumns[row] = i;
                placeQueen(usedColumns, row + 1);
            }
        }
    }
    
    public boolean isValid(int[] usedColumns, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (usedColumns[i] == col) {
                return false;
            }
            if ((row - i) == Math.abs(col-usedColumns[i])) {
                return false;
            }
        }
        return true;
    }
}