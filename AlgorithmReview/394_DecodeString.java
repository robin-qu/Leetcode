// class Solution {
//     public String decodeString(String s) {
//         if (s == null || s.length() == 0) {
//             return "";
//         }
        
//         Stack<Integer> intStack = new Stack<>();
//         Stack<String> strStack = new Stack<>();
//         String res = "";
//         int count = 0;
        
//         for (int i = 0; i < s.length(); i++) {
//             char c = s.charAt(i);
//             if (Character.isDigit(c)) {
//                 count = count * 10 + (c - '0');
//             } else if (c == '[') {
//                 intStack.push(count);
//                 count = 0;
//                 strStack.push(res);
//                 res = "";
//             } else if (c == ']') {
//                 int repeat = intStack.pop();
//                 String temp = strStack.pop();
//                 for (int j = 0; j < repeat; j++) {
//                     temp += res;
//                 }
//                 res = temp;
//             } else {
//                 res += c;
//             }
//         }
        
//         return res;
//     }
// }


// Use only one stack
class Solution {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        Stack<Object> stack = new Stack<>();
        String res = "";
        int count = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                count = count * 10 + (c - '0');
            } else if (c == '[') {
                stack.push(res);
                stack.push(count);
                count = 0;
                res = "";
            } else if (c == ']') {
                while (!stack.isEmpty() && !(stack.peek() instanceof Integer)) {
                    res = stack.pop() + res;
                }
                int repeat = (Integer) stack.pop();
                String temp = "";
                for (int j = 0; j < repeat; j++) {
                    temp += res;
                }
                res = temp;
            } else {
                res += c;
            }
        }
        
        while (!stack.isEmpty()) {
            res = (String) stack.pop() + res;
        }
        
        return res;
    }
}