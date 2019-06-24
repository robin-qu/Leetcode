public class Solution {
    /**
     * @param expression: a list of strings
     * @return: an integer
     */
    public int evaluateExpression(String[] expression) {
        if (expression == null || expression.length == 0) {
            return 0;
        }
        
        List<String> polish = toPolish(expression);
        
        Stack<Object> stack = new Stack<>();
        
        for (int i = polish.size() - 1; i >= 0; i--) {
            String curr = polish.get(i);
            if (!isOperator(curr)) {
                Integer val = Integer.parseInt(curr);
                stack.push(val);
            } else {
                Integer a = (Integer) stack.pop();
                Integer b = (Integer) stack.pop();
                if (curr.equals("+")) {
                    stack.push(a + b);
                } else if (curr.equals("-")) {
                    stack.push(a - b);
                } else if (curr.equals("*")) {
                    stack.push(a * b);
                } else {
                    stack.push(a / b);
                }
            }
        }
        
        return stack.isEmpty() ? 0 : (Integer) stack.peek();
    }
    
    private List<String> toPolish(String[] expression) {
        List<String> list = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        
        for (int i = expression.length - 1; i >= 0; i--) {
            String curr = expression[i];
            
            if (curr.equals(")")) {
                stack.push(curr);
            } else if (curr.equals("(")) {
                while (!stack.peek().equals(")")) {
                    list.add(stack.pop());
                }
                stack.pop();
            } else if (isOperator(curr)) {
                while (!stack.isEmpty() && getPriority(stack.peek()) > getPriority(curr)) {
                    list.add(stack.pop());
                }
                stack.push(curr);
            } else {  // is number
                list.add(curr);
            }
        }
        
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        
        Collections.reverse(list);
        
        return list;
    }
    
    private int getPriority(String s) {
        if (s.equals("*") || s.equals("/")) {
            return 3;
        }
        
        if (s.equals("+") || s.equals("-")) {
            return 2;
        }
        
        if (s.equals(")") || s.equals("(")) {
            return 0;
        }
        
        return 1;  // number
    }
    
    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }
}