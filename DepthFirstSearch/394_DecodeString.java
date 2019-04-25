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


class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        String res = "";
        int count = 0;
        int i = 0;
        
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                count = count * 10 + (s.charAt(i) - '0');
            } else if (s.charAt(i) == '[') {
                countStack.push(count);
                count = 0;
                stringStack.push(res);
                res = "";
            } else if (s.charAt(i) == ']') {
                int repeat = countStack.pop();
                String front = stringStack.pop();
                for (int j = 0; j < repeat; j++) {
                    front += res;
                }
                res = front;
            } else {
                res += s.charAt(i);
            }
            i++;
        }
        return res;
    }
}