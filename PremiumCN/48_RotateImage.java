class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        transpose(matrix, m, n);
        swapColumn(matrix, m, n);
    }

    private void transpose(int[][] matrix, int m, int n) {
        for (int i = 0; i < m - 1; i++) {
            for (int j = 1 + i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private void swapColumn(int[][] matrix, int m, int n) {
        for (int i = 0; i < m; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
} 