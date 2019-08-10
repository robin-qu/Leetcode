// Initial version  O(n)time O(n)space
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        char[] ss = s.toCharArray();
        Stack<Object> stack = new Stack<>();
        char sign = '+';
        
        for (int i = 0; i < n; i++) {
            char c = ss[i];
            
            if (c == ' ') {
                continue;
            } else if (Character.isDigit(c)) {
                int num = c - '0';
                while (i + 1 < n && Character.isDigit(ss[i + 1])) {
                    i++;
                    num = num * 10 + (ss[i] - '0');
                }
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(0 - num);
                } else if (sign == '*') {
                    stack.push((int) stack.pop() * num);
                } else if (sign == '/') {
                    stack.push((int) stack.pop() / num);
                }
            } else if (c == '(') {
                stack.push(sign);
                sign = '+';
            } else if (c == ')') {
                int resInParen = 0;
                while (!(stack.peek() instanceof Character)) {  // within parentheses
                    resInParen += (int) stack.pop();
                }
                
                char operator = (char) stack.pop();
                if (operator == '+') {
                    stack.push(resInParen);
                } else if (operator == '-') {
                    stack.push(-resInParen);
                } else if (operator == '*') {
                    stack.push((int) stack.pop() * resInParen);
                } else if (operator == '/') {
                    stack.push((int) stack.pop() / resInParen);
                }
            } else {  // is operator
                sign = c;
            }
        }
        
        int res = 0;
        while (!stack.isEmpty()) {
            res += (int) stack.pop();
        }
        
        return res;        
    }
}