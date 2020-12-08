class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        s = s.replace(" ", "");
        Deque<Integer> stack = new ArrayDeque<>();
        int n = s.length();
        int i = 0;
        char sign = '+';
        char[] ss = s.toCharArray();
        while (i < n) {
            if (Character.isDigit(ss[i])) {
                int num = 0;
                while (i < n && Character.isDigit(ss[i])) {
                    num = num * 10 + ss[i] - '0';
                    i++;
                }
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
            } else {
                sign = ss[i];
                i++;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}