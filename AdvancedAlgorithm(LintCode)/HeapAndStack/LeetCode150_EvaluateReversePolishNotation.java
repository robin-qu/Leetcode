class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        
        Stack<Object> stack = new Stack<>();
        
        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            
            if (isOperator(s)) {
                Integer a = (Integer) stack.pop();
                Integer b = (Integer) stack.pop();
                
                if (s.equals("+")) {
                    stack.push(b + a);
                } else if (s.equals("-")) {
                    stack.push(b - a);
                } else if (s.equals("*")) {
                    stack.push(b * a);
                } else {
                    stack.push(b / a);
                }
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        
        return stack.isEmpty() ? 0 : (Integer) stack.peek();
    }
    
    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }
}