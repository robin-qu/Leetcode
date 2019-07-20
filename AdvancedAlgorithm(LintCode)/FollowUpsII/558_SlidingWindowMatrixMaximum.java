// // DP O(mn)time O(mn)space
// public class Solution {
//     /**
//      * @param matrix: an integer array of n * m matrix
//      * @param k: An integer
//      * @return: the maximum number
//      */
//     public int maxSlidingMatrix(int[][] matrix, int k) {
//         if (matrix == null || matrix.length < k ||
//             matrix[0] == null || matrix[0].length < k) {
//             return 0;
//         }
        
//         int m = matrix.length;
//         int n = matrix[0].length;
        
//         int[][] sum = new int[m][n];  // sum[i][j]  represents the sum of matrix whose right-bottom element is at i, j
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (i == 0 && j == 0) {
//                     sum[i][j] = matrix[i][j];
//                 } else if (i == 0) {
//                     sum[i][j] = matrix[i][j] + sum[i][j - 1];
//                 } else if (j == 0) {
//                     sum[i][j] = matrix[i][j] + sum[i - 1][j];
//                 } else {
//                     sum[i][j] = matrix[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
//                 }
//             }
//         }
        
//         int max = Integer.MIN_VALUE;
//         int currSum = 0;
//         for (int i = k - 1; i < m; i++) {
//             for (int j = k - 1; j < n; j++) {
//                 if (i == k - 1 && j == k - 1) {
//                     currSum = sum[i][j];
//                 } else if (i == k - 1) {
//                     currSum = sum[i][j] - sum[i][j - k];
//                 } else if (j == k - 1) {
//                     currSum = sum[i][j] - sum[i - k][j];
//                 } else {
//                     currSum = sum[i][j] - sum[i - k][j] - sum[i][j - k] + sum[i - k][j - k];
//                 }
                
//                 max = Math.max(max, currSum);
//             }
//         }
        
//         return max;
//     }
// } 


// DP O(mn)time O(n)space
public class Solution {
    /**
     * @param matrix: an integer array of n * m matrix
     * @param k: An integer
     * @return: the maximum number
     */
    public int maxSlidingMatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length < k ||
            matrix[0] == null || matrix[0].length < k) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[] sum = new int[n + 1];  // sum[i][j]  represents the sum of matrix whose right-bottom element is at i, j
        
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            int currSum = 0;
            for (int j = 0; j < n; j++) {
                sum[j] += matrix[i][j];  // add new row
                if (i >= k) {
                    sum[j] -= matrix[i - k][j];  // remove row outside the window
                }
                
                currSum += sum[j];  // add new column
                if (j >= k) {
                    currSum -= sum[j - k];  // remove col outside the window
                }
                
                if (i >= k - 1 && j >= k - 1) {
                    max = Math.max(max, currSum);
                }
            }
        }
        
        return max;
    }
}  