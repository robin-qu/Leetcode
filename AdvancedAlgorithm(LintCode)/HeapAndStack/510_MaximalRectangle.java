public class Solution {
    /**
     * @param matrix: a boolean 2D matrix
     * @return: an integer
     */
    public int maximalRectangle(boolean[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] heights = getHeights(matrix);
        int max = 0;
        
        for (int i = 0; i < m; i++) {
            max = Math.max(max, getMax(heights[i]));
        }
        
        return max;
    }
    
    private int[][] getHeights(boolean[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[m][n];
        
        for (int i = 0; i < n; i++) {
            res[0][i] = matrix[0][i] ? 1 : 0;
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j]) {
                    res[i][j] += 1 + res[i - 1][j];
                }
            }
        }
        
        return res;
    }
    
    private int getMax(int[] heights) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i <= heights.length; i++) {
            int val = (i == heights.length ? 0 : heights[i]);
            
            while (!stack.isEmpty() && val < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                res = Math.max(res, h * w);
            }
            
            stack.push(i);
        }
        
        return res;
    }
}