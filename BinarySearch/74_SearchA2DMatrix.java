class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        int low = 0;
        int high = row * col - 1;
     
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (matrix[mid / col][mid % col] == target) {
                return true;
            } else if (target < matrix[mid / col][mid % col]) {
                high = mid;
            } else {
                low = mid;
            }
        }
        
        return matrix[low / col][low % col] == target || matrix[high / col][high % col] == target;
    }
}