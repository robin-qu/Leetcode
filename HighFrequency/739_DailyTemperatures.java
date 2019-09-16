// monotonic stack O(n)time O(n)space
class Solution {
    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) {
            return new int[0];
        }
        
        int n = T.length;
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[n];
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int idx = stack.pop();
                res[idx] = i - idx;
            }
            
            stack.push(i);
        }
        
        return res;
    }
}