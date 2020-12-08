class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = s.replace(" ", "");
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        char[] ss = s.toCharArray();
        int i = 0;
        int curr = 0;
        int sign = 1;
        while (i < n) {
            if (Character.isDigit(ss[i])) {
                int num = 0;
                while (i < n && Character.isDigit(ss[i])) {
                    num = num * 10 + ss[i] - '0';
                    i++;
                }
                curr += sign * num;
            } else if (ss[i] == '+') {
                sign = 1;
                i++;
            } else if (ss[i] == '-') {
                sign = -1;
                i++;
            } else if (ss[i] == '(') {
                stack.push(curr);
                stack.push(sign);
                curr = 0;
                sign = 1;
                i++;
            } else {
                curr = curr * stack.pop() + stack.pop();
                i++;
            }
        }

        return curr;
    }
}