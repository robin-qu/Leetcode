class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {      // upper-left corner
                    dp[i][j] = grid[i][j];
                } else if (j == 0) {         // first column
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                } else if (i == 0) {         // first row
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                } else {                     // others
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m - 1][n - 1];
    }
}