// Stack O(n)time O(n)space
class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        
        int n = tokens.length;
        Stack<Integer> stack = new Stack<>();
        
        for (String s : tokens) {
            if (isOpr(s)) {
                int b = stack.pop();
                int a = stack.pop();
                if (s.equals("+")) {
                    stack.push(a + b);
                } else if (s.equals("-")) {
                    stack.push(a - b);
                } else if (s.equals("*")) {
                    stack.push(a * b);
                } else {
                    stack.push(a / b);
                }
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        
        return stack.peek();
    }
    
    private boolean isOpr(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }
}