// stack O(n)time O(n)space
class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        s = s.trim();
        char[] ss = s.toCharArray();
        
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            if (ss[i] == ' ') {
                if (i > 0 && ss[i - 1] != ' ') {
                    stack.push(sb.toString());
                    sb = new StringBuilder();
                }
            } else {
                sb.append(ss[i]);
            }
        }
        
        stack.push(sb.toString());
        sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
            sb.append(' ');
        }
        
        return sb.toString().trim();
    }
}