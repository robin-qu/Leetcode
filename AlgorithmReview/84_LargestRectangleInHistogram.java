class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        
        for (int i = 0; i <= heights.length; i++) {
            int curr = (i == heights.length ? -1 : heights[i]);
            
            while (!stack.isEmpty() && curr < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int w = (stack.isEmpty() ? i : i - stack.peek() - 1);
                res = Math.max(res, h * w);
            }
            
            stack.push(i);
        }
        
        return res;
    }
}