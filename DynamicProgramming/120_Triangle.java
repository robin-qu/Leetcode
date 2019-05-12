// // Traverse
// class Solution {
//     public int min = Integer.MAX_VALUE;
    
//     public int minimumTotal(List<List<Integer>> triangle) {
//         if (triangle == null || triangle.size() == 0) {
//             return 0;
//         }
        
//         helper(triangle, 0, 0, 0);
        
//         return min;
//     }
    
//     private void helper(List<List<Integer>> triangle, int row, int col, int sum) {
//         if (row == triangle.size() - 1) {
//             min = Math.min(min, sum + triangle.get(row).get(col));
//             return;
//         }
        
//         helper(triangle, row + 1, col, sum + triangle.get(row).get(col));
//         helper(triangle, row + 1, col + 1, sum + triangle.get(row).get(col));
//     }
// }


// // Divide and Conquer
// class Solution {
//     public int minimumTotal(List<List<Integer>> triangle) {
//         if (triangle == null || triangle.size() == 0) {
//             return 0;
//         }
        
//         return helper(triangle, 0, 0);
//     }
    
//     private int helper(List<List<Integer>> triangle, int row, int col) {
//         if (row == triangle.size() - 1) {
//             return triangle.get(row).get(col);
//         }
        
//         int leftMin = helper(triangle, row + 1, col);
//         int rightMin = helper(triangle, row + 1, col + 1);
        
//         return triangle.get(row).get(col) + Math.min(leftMin, rightMin);
//     }
// }

// // memorization search
// class Solution {
//     public int minimumTotal(List<List<Integer>> triangle) {
//         if (triangle == null || triangle.size() == 0) {
//             return 0;
//         }

//         int n = triangle.size();
//         int[][] dp = new int[n][triangle.get(n - 1).size()];
        
//         for (int i = 0; i < dp.length; i++) {
//             for (int j = 0; j < dp[0].length; j++) {
//                 dp[i][j] = Integer.MAX_VALUE;
//             }
//         }
        
//         return helper(triangle, dp, 0, 0);
//     }
    
//     private int helper(List<List<Integer>> triangle, int[][] dp,
//                        int row, int col) {
//         if (row == triangle.size() - 1) {
//             return triangle.get(row).get(col);
//         }
        
//         if (dp[row][col] != Integer.MAX_VALUE) {
//             return dp[row][col];
//         }
        
//         int leftMin = helper(triangle, dp, row + 1, col);
//         int rightMin = helper(triangle, dp, row + 1, col + 1);
//         dp[row][col] = triangle.get(row).get(col) + Math.min(leftMin, rightMin);
        
//         return dp[row][col];
//     }
// }


// // top - bottom
// class Solution {
//     public int minimumTotal(List<List<Integer>> triangle) {
//         if (triangle == null || triangle.size() == 0) {
//             return 0;
//         }
        
//         int n = triangle.size();
//         int[][] dp = new int[n][triangle.get(n - 1).size()];
        
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j <= i; j++) {
//                 if (i == 0 && j == 0) {
//                     dp[i][j] = triangle.get(i).get(j);
//                 } else if (j == 0) {
//                     dp[i][j] = triangle.get(i).get(j) + dp[i - 1][j];
//                 } else if (j == i) {
//                     dp[i][j] = triangle.get(i).get(j) + dp[i - 1][j - 1];
//                 } else {
//                     dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i - 1][j], dp[i - 1][j - 1]);
//                 }
//             }
//         }
        
//         int min = Integer.MAX_VALUE;
//         for (int i = 0; i < n; i++) {
//             min = Math.min(min, dp[n - 1][i]);
//         }
        
//         return min;
//     }
// }


// bottom - top
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        
        int n = triangle.size();
        int[][] dp = new int[n][triangle.get(n - 1).size()];
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if (i == n - 1) {
                    dp[i][j] = triangle.get(i).get(j);
                } else {
                    dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
                }
            }
        }
        
        return dp[0][0];
    }
}
