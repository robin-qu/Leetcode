class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i <= n; i++) {
            int currH = i < n ? heights[i] : -1;
            while (!stack.isEmpty() && heights[stack.peek()] > currH) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                res = Math.max(res, h * w);
            }
            
            stack.push(i);
        }
        
        return res;
    }
}