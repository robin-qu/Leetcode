class Solution {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        Stack<Object> stack = new Stack<>();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isLetter(c)) {
                stack.push(c);
            } else if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                stack.push(num);
                num = 0;
            } else {   // c == ']'
                String temp = "";
                while (!(stack.peek() instanceof Integer)) {
                    temp = stack.pop() + temp;
                }
                Integer repeat = (Integer) stack.pop();
                String repeatedString = "";
                for (int j = 0; j < repeat; j++) {
                    repeatedString += temp;
                }
                
                stack.push(repeatedString);
            }
        }
        
        String res = "";
        while (!stack.isEmpty()) {
            res = stack.pop() + res;
        }
        
        return res;
    }
}