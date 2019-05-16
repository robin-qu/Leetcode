class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int len = matrix.length * matrix[0].length;
        
        int left = 0;
        int right = len - 1;
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            if (target < getValue(matrix, mid)) {
                right = mid;
            } else if (getValue(matrix, mid) < target) {
                left = mid;
            } else {
                return true;
            }
        }
        
        return getValue(matrix, left) == target || getValue(matrix, right) == target;
    }
    
    private int getValue(int[][] matrix, int idx) {
        return matrix[idx / matrix[0].length][idx % matrix[0].length];
    }
}