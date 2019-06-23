public class Solution {
    /**
     * @param expression: A string array
     * @return: The Reverse Polish notation of this expression
     */
    public List<String> convertToRPN(String[] expression) {
        if (expression == null || expression.length == 0) {
            return new ArrayList<>();
        }
        
        List<String> res = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        
        for (int i = 0; i < expression.length; i++) {
            String curr = expression[i];
            
            if (curr.equals(")")) {
                while (!stack.peek().equals("(")) {
                    res.add(stack.pop());
                }
                stack.pop();
            } else if (curr.equals("(")) {
                stack.push(curr);
            } else if (isOperator(curr)) {
                while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(curr)) {
                    res.add(stack.pop());
                }
                stack.push(curr);
            } else {  // is number
                res.add(curr);
            }
        }
        
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        
        return res;
    }
    
    private int getPriority(String s) {
        if (s.equals("*") || s.equals("/")) {
            return 3;
        }
        
        if (s.equals("+") || s.equals("-")) {
            return 2;
        }
        
        if (s.equals(")")) {
            return 0;
        }
        
        return 1;  // numbers
    }
    
    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }
}