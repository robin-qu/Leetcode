// // DFS (MLE)
// public class Solution {
//     /**
//      * @param matrix: A 2D-array of integers
//      * @return: an integer
//      */
//     public int longestContinuousIncreasingSubsequence2(int[][] matrix) {
//         if (matrix == null || matrix.length == 0 ||
//             matrix[0] == null || matrix[0].length == 0) {
//             return 0;
//         }
        
//         int m = matrix.length;
//         int n = matrix[0].length;
//         int max = 0;
        
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 max = Math.max(max, dfs(matrix, i, j, 1));
//             }
//         }
        
//         return max;
//     }
    
//     private int dfs(int[][] matrix, int i, int j, int len) {
//         int m = matrix.length;
//         int n = matrix[0].length;
//         int[] di = new int[]{0, 1, -1, 0};
//         int[] dj = new int[]{1, 0, 0, -1};
//         int max = len;
        
//         for (int k = 0; k < 4; k++) {
//             int ni = i + di[k];
//             int nj = j + dj[k];
//             if (ni >= 0 && ni < m && nj >= 0 && nj < n && matrix[ni][nj] > matrix[i][j]) {
//                 max = Math.max(max, dfs(matrix, ni, nj, len + 1));
//             }
//         }
        
//         return max;
//     }
// }


// Memorization search
public class Solution {
    /**
     * @param matrix: A 2D-array of integers
     * @return: an integer
     */
    public int longestContinuousIncreasingSubsequence2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];  // length of longest path started from matrix[m][n]
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }
        int max = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = dfs(matrix, i, j, memo);
                max = Math.max(max, memo[i][j]);
            }
        }
        
        return max;
    }
    
    private int dfs(int[][] matrix, int i, int j, int[][] memo) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[] di = new int[]{0, 1, -1, 0};
        int[] dj = new int[]{1, 0, 0, -1};
        int max = 1;
        
        for (int k = 0; k < 4; k++) {
            int ni = i + di[k];
            int nj = j + dj[k];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && matrix[ni][nj] > matrix[i][j]) {
                memo[ni][nj] = dfs(matrix, ni, nj, memo);
                max = Math.max(max, memo[ni][nj] + 1);
            }
        }
        
        return max;
    }
}