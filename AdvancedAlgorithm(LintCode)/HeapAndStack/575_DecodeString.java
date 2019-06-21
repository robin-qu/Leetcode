public class Solution {
    /**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
    public String expressionExpand(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        Stack<Object> stack = new Stack<>();
        int count = 0;
        String str = "";
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                count = 10 * count + (c - '0');
            } else if (c == '[') {
                stack.push(str);
                str = "";
                stack.push(Integer.valueOf(count));
                count = 0;
            } else if (c == ']') {
                while (stack.peek() instanceof String) {
                    str = (String) stack.pop() + str;
                }
                String temp = "";
                if (stack.peek() instanceof Integer) {
                    Integer repeat = (Integer) stack.pop();
                    for (int j = 0; j < repeat; j++) {
                        temp += str;
                    }
                }
                stack.push(temp);
                str = "";
            } else {
                str += c;
            }
        }
        
        while (!stack.isEmpty()) {
            str = (String) stack.pop() + str;
        }
        
        return str;
    }
}