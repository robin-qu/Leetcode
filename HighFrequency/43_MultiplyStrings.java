// // brute force (Overflow)
// class Solution {
//     public String multiply(String num1, String num2) {
//         if (num1 == null || num1.length() == 0 ||
//             num2 == null || num2.length() == 0) {
//             return "";
//         }
        
//         int n1 = num1.length();
//         int n2 = num2.length();
//         char[] s1 = num1.toCharArray();
//         char[] s2 = num2.toCharArray();
        
//         long res = 0;
//         int resPow = 1;
//         for (int i = n2 - 1; i >= 0; i--) {
//             long sum = 0;
//             int carry = 0;
//             int power = 1;
//             for (int j = n1 - 1; j >= 0; j--) {
//                 int curr = (carry + (s2[i] - '0') * (s1[j] - '0'));
//                 sum += curr % 10 * power;
//                 carry = curr / 10;
//                 power *= 10;
//             }
//             if (carry > 0) {
//                 sum += carry * power;
//             }
            
//             res += sum * resPow;
//             resPow *= 10;
//         }
        
//         if (res == 0) {
//             return "0";
//         }
        
//         StringBuilder sb = new StringBuilder();
//         while (res > 0) {
//             sb.append(res % 10);
//             res /= 10;
//         }
        
//         return sb.reverse().toString();
//     }
// }


// brute force (Overflow)  O(nm)time O(n + m)space
class Solution {
    public String multiply(String num1, String num2) {
        if (num1 == null || num1.length() == 0 ||
            num2 == null || num2.length() == 0) {
            return "";
        }
        
        int n1 = num1.length();
        int n2 = num2.length();
        char[] s1 = num1.toCharArray();
        char[] s2 = num2.toCharArray();
        
        int[] pos = new int[n1 + n2];
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int temp = (s1[i] - '0') * (s2[j] - '0');
                int pos1 = i + j;
                int pos2 = i + j + 1;
                int sum = temp + pos[pos2];
                
                pos[pos2] = sum % 10;
                pos[pos1] += sum / 10;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int n : pos) {
            if (!(sb.length() == 0 && n == 0)) {
                sb.append(n);
            }
        }
        
        return sb.length() == 0 ? "0" : sb.toString();
    }
}