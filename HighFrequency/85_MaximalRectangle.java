// Stack O(mn)time O(n)space
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[] row = new int[n];
        int max = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    row[j] = 0;
                } else {
                    row[j]++;
                }
            }
            
            max = Math.max(max, maxHistogram(row));
        }
        
        return max;
    }
    
    private int maxHistogram(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();  // monotonic increasing stack, stores the index of nums
        int max = 0;
        
        for (int i = 0; i <= n; i++) {
            int curr = (i == n ? 0 : nums[i]);
            while (!stack.isEmpty() && nums[stack.peek()] > curr) {
                int h = nums[stack.pop()];
                int w = stack.isEmpty() ? i : i - (stack.peek() + 1);
                max = Math.max(max, h * w);
            }            
            
            stack.push(i);
        }
        
        return max;
    }
}