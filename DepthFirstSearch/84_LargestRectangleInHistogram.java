// // Brute Force
// class Solution {
//     public int largestRectangleArea(int[] heights) {
//         int res = 0;
        
//         if (heights == null || heights.length == 0) {
//             return res;
//         }
        
//         for (int height : heights) {
//             res = Math.max(res, getRect(height, heights));
//         }
//         return res;
//     }
    
//     // Finds the largest rectangle of the given hight
//     private int getRect(int height, int[] heights) {
//         int max = 0;
//         int area = 0;
//         for (int i = 0; i < heights.length; i++) {
//             if (heights[i] >= height) {
//                 area += height;
//                 max = Math.max(area, max);
//             } else {
//                 area = 0;
//             }
//         }
//         return max;
//     }
// }

// Stack
public class Solution {
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        
        if (heights == null || heights.length == 0) {
            return max;
        }
        
        Stack<Integer> stack = new Stack<Integer>(); 
        
        for (int i = 0; i <= heights.length; i++) {
            int curr = (i == heights.length ? -1 : heights[i]);
            while (!stack.isEmpty() && curr <= heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int l = (stack.isEmpty() ? i : i - stack.peek() - 1);
                max = Math.max(max, h * l);
            }
            stack.push(i);
        }
        return max;
    }
}