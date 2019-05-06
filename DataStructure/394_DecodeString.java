// class Solution {
//     public String decodeString(String s) {
//         Stack<Integer> countStack = new Stack<>();
//         Stack<String> stringStack = new Stack<>();
//         String res = "";
//         int i = 0;
        
//         while (i < s.length()) {
//             if (Character.isDigit(s.charAt(i))) {
//                 int count = 0;
//                 while (Character.isDigit(s.charAt(i))) {
//                     count = count * 10 + (s.charAt(i) - '0');
//                     i++;
//                 }
//                 countStack.push(count);
//             } else if (s.charAt(i) == '[') {
//                 stringStack.push(res);
//                 res = "";
//                 i++;
//             } else if (s.charAt(i) == ']') {
//                 int repeat = countStack.pop();
//                 String front = stringStack.pop();
//                 for (int j = 0; j < repeat; j++) {
//                     front += res;
//                 }
//                 res = front;
//                 i++;
//             } else {
//                 res += s.charAt(i);
//                 i++;
//             }
//         }
//         return res;
//     }
// }


// class Solution {
//     public String decodeString(String s) {
//         Stack<Integer> countStack = new Stack<>();
//         Stack<String> stringStack = new Stack<>();
//         String res = "";
//         int count = 0;
//         int i = 0;
        
//         while (i < s.length()) {
//             if (Character.isDigit(s.charAt(i))) {
//                 count = count * 10 + (s.charAt(i) - '0');
//             } else if (s.charAt(i) == '[') {
//                 countStack.push(count);
//                 count = 0;
//                 stringStack.push(res);
//                 res = "";
//             } else if (s.charAt(i) == ']') {
//                 int repeat = countStack.pop();
//                 String front = stringStack.pop();
//                 for (int j = 0; j < repeat; j++) {
//                     front += res;
//                 }
//                 res = front;
//             } else {
//                 res += s.charAt(i);
//             }
//             i++;
//         }
//         return res;
//     }
// }

class Solution {
    public String decodeString(String s) {
        Stack<Object> stack = new Stack<>();
        
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                stack.push(String.valueOf(c));
            } else if (c >= '0' && c <= '9') {
                count = count * 10 + (c - '0');
            } else if (c == '[') {
                stack.push(count);
                count = 0;
            } else {    // right bracket
                String str = "";
                while (!stack.isEmpty() && !(stack.peek() instanceof Integer)) {
                    str = stack.pop() + str;
                }
                if (!stack.isEmpty()) {  // integer
                    int repeat = (int) stack.pop();
                    for (int j = 0; j < repeat; j++) {
                        stack.push(str);
                    }
                }
            }
        }
        
        String res = "";
        while (!stack.isEmpty()) {
            res = (String) stack.pop() + res;
        }
        return res;
    }
}