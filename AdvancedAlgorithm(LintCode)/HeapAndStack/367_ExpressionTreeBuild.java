/**
 * Definition of ExpressionTreeNode:
 * public class ExpressionTreeNode {
 *     public String symbol;
 *     public ExpressionTreeNode left, right;
 *     public ExpressionTreeNode(String symbol) {
 *         this.symbol = symbol;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /*
     * @param expression: A string array
     * @return: The root of expression tree
     */
    public ExpressionTreeNode build(String[] expression) {
        if (expression == null || expression.length == 0) {
            return null;
        }
        
        List<String> polish = toPolish(expression);
        
        Stack<ExpressionTreeNode> stack = new Stack<>();
        
        for (int i = polish.size() - 1; i >= 0; i--) {
            String curr = polish.get(i);
            
            if (!isOperator(curr)) {
                stack.push(new ExpressionTreeNode(curr));
            } else {
                ExpressionTreeNode root = new ExpressionTreeNode(curr);
                root.left = stack.pop();
                root.right = stack.pop();
                stack.push(root);
            }
        }
        
        return stack.isEmpty() ? null : stack.peek();
    }
    
    private List<String> toPolish(String[] expression) {
        Stack<String> stack = new Stack<>();
        List<String> list = new ArrayList<>();
        
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
            } else { // is number
                list.add(curr);
            }
        }
        
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        
        Collections.reverse(list);
        
        return list;
    }
    
    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
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
        
        return 1;
    }
}