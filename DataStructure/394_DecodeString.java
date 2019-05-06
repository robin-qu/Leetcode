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

// Recursive
class Solution {
    private int pos;
    
    public String decodeString(String s) {
        pos = 0;
        return helper(s);
    }
    
    private String helper(String s) {
        int count = 0;
        String str = "";
        
        for (; pos < s.length(); pos++) {
            char c = s.charAt(pos);
            
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                str += c;
            } else if (c >= '0' && c <= '9') {
                count = count * 10 + c - '0';
            } else if (c == '[') {
                pos++;
                String sub = helper(s);
                for (int i = 0; i < count; i++) {
                    str += sub;
                }
                count = 0;
            } else {
                return str;
            }
        }
        
        return str;
    }
}