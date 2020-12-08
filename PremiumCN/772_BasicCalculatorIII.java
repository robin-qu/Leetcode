class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        s = s.replace(" ", "");
        Deque<Object> stack = new ArrayDeque<>();
        char[] ss = s.toCharArray();
        char sign = '+';
        int curr = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (Character.isDigit(ss[i])) {
                int num = 0;
                while (i < n && Character.isDigit(ss[i])) {
                    num = num * 10 + ss[i] - '0';
                    i++;
                }
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push((Integer) stack.pop() * num);
                } else {
                    stack.push((Integer) stack.pop() / num);
                }
            } else if (ss[i] == '(') {
                stack.push(sign);
                sign = '+';
                i++;
            } else if (ss[i] == ')') {
                int valInParen = 0;
                while (stack.peek() instanceof Integer) {
                    valInParen += (Integer) stack.pop();
                }
                char signBeforeParen = (char) stack.pop();
                if (signBeforeParen == '+') {
                    stack.push(valInParen);
                } else if (signBeforeParen == '-') {
                    stack.push(-valInParen);
                } else if (signBeforeParen == '*') {
                    stack.push((Integer) stack.pop() * valInParen);
                } else {
                    stack.push((Integer) stack.pop() / valInParen);
                }
                i++;
            } else {
                sign = ss[i];
                i++;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += (int) stack.pop();
        }

        return res;
    }
}