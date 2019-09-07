// O(mn)time O(1)space
class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        
        transpose(matrix);
        reverseRow(matrix);
    }
    
    private void transpose(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(i, j, j, i, matrix);
            }
        }
    }
    
    private void reverseRow(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                swap(i, j, i, n - j - 1, matrix);
            }
        }
    }
    
    // if rotate counter-clockwise, reverseCol
    private void reverseCol(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                swap(j, i, n - j - 1, i, matrix);
            }
        }
    }
    
    private void swap(int i, int j, int x, int y, int[][] matrix) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[x][y];
        matrix[x][y] = temp;
    }
}