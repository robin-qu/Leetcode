// stack O(n)time O(1)space
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        char[] ss = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        
        for (int i = 0; i < n; i++) {
            char c = ss[i];
            
            if (c == ' ') {
                continue;
            } else if (Character.isDigit(c)) {
                // extract number
                int num = 0;
                while (i < n && Character.isDigit(ss[i])) {
                    num = num * 10 + (ss[i] - '0');
                    i++;
                }
                i--;
                
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else {
                    stack.push(stack.pop() / num);
                }
            } else {
                sign = c;
            }
        }
        
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        
        return res;
    }
}


// // stack O(n)time O(1)space
// class Solution {
//     public int calculate(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }
        
//         int n = s.length();
//         char[] ss = s.toCharArray();
//         char sign = '+';
//         long res = 0;
//         long prev = 0;
        
//         for (int i = 0; i < n; i++) {
//             char c = ss[i];
            
//             if (c == ' ') {
//                 continue;
//             }
            
//             if (Character.isDigit(c)) {
//                 int num = ss[i] - '0';
//                 while (i + 1 < n && Character.isDigit(ss[i + 1])) {
//                     num = num * 10 + ss[i + 1] - '0';
//                     i++;
//                 }
                
//                 if (sign == '+') {
//                     res += prev;
//                     prev = num;
//                 } else if (sign == '-') {
//                     res += prev;
//                     prev = -num;
//                 } else if (sign == '*') {
//                     prev *= num;
//                 } else if (sign == '/') {
//                     prev /= num;
//                 }
                
//                 num = 0;
//             }
            
//             sign = c;
//         }
        
//         return (int) res + (int) prev;
//     }
// }


