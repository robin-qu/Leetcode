// // Stack O(n)time O(n)space
// class Solution {
//     public int calculate(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }
        
//         int n = s.length();
//         Stack<Object> stack = new Stack<>();
//         int num = 0;
        
//         for (int i = 0; i < n; i++) {
//             char c = s.charAt(i);
            
//             if (c == ' ') {
//                 continue;
//             } else if (c == '(') {
//                 stack.push(c);
//             } else if (c == ')') {
//                 int temp = (Integer) stack.pop();
//                 stack.pop(); // pop '('
//                 evaluate(stack, temp);
//             } else if (Character.isDigit(c)) {
//                 num = c - '0';
//                 while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {  // extract the number
//                     i++;
//                     num = num * 10 + s.charAt(i) - '0';
//                 }
                
//                 evaluate(stack, num);
//                 num = 0;
//             } else {  // c is operator
//                 stack.push(c);
//             }
//         }
        
//         return (Integer) stack.peek();
//     }
    
//     private void evaluate(Stack<Object> stack, int num) {
//         if (stack.isEmpty()) {
//             stack.push(num);
//         } else if ((stack.peek() instanceof Character) && ((Character) stack.peek()) == '+') {
//             stack.pop();
//             stack.push((Integer) stack.pop() + num);
//         } else if ((stack.peek() instanceof Character) && ((Character) stack.peek()) == '-') {
//             stack.pop();
//             stack.push((Integer) stack.pop() - num);
//         } else {
//             stack.push(num);
//         }
//     }
// }


// Stack O(n)time O(n)space
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        char[] ss = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        int sign = 1;
        int res = 0;
        
        for (int i = 0; i < n; i++) {
            char c = ss[i];
            
            if (c == ' ') {
                continue;
            }
            
            if (Character.isDigit(c)) {
                int num = c - '0';
                while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
                    i++;
                    num = num * 10 + s.charAt(i) - '0';
                }
                
                res += sign * num;
            } else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                // begin a new expression, reset res and sign
                res = 0;
                sign = 1;
            } else if (c == ')') {
                res = stack.pop() * res;  // sign
                res += stack.pop(); // previous result
            } else {  // operator
                sign = (c == '+' ? 1 : -1);
            }
        }
        
        return res;
    }
}