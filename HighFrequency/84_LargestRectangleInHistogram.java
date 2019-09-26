// stack O(n)time O(n)space
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        
        for (int i = 0; i <= n; i++) {
            int curr = i < n ? heights[i] : -1;
            while (!stack.isEmpty() && heights[stack.peek()] > curr) {
                int h = heights[stack.pop()];
                int w = i - (stack.isEmpty() ? 0 : stack.peek() + 1);
                max = Math.max(max, h * w);
            }
            
            stack.push(i);
        }
        
        return max;
    }
}