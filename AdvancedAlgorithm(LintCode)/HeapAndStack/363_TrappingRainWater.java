// // monotonic stack
// public class Solution {
//     /**
//      * @param heights: a list of integers
//      * @return: a integer
//      */
//     public int trapRainWater(int[] heights) {
//         if (heights == null || heights.length == 0) {
//             return 0;
//         }
        
//         // decreasing monotonic stack
//         Stack<Integer> stack = new Stack<>();
//         int res = 0;
        
//         for (int i = 0; i < heights.length; i++) {
//             while (!stack.isEmpty() && heights[i] > heights[stack.peek()]) {
//                 int idx = stack.pop();
//                 if (stack.isEmpty()) {
//                     continue;
//                 }
//                 int h = Math.min(heights[stack.peek()], heights[i]) - heights[idx];
//                 int w = i - stack.peek() - 1;
                
//                 res += h * w;
//             }
            
//             stack.push(i);
//         }
        
//         return res;
//     }
// }

// two pointers
public class Solution {
    /**
     * @param heights: a list of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = heights.length - 1;
        int leftheight = heights[left];
        int rightheight = heights[right];
        int res = 0;
        
        while (left < right) {
            if (leftheight < rightheight) {
                left++;
                if (heights[left] < leftheight) {
                    res += leftheight - heights[left];
                } else {
                    leftheight = heights[left];
                }
            } else {
                right--;
                if (heights[right] < rightheight) {
                    res += rightheight - heights[right];
                } else {
                    rightheight = heights[right];
                }
            }
        }
        
        return res;
    }
}