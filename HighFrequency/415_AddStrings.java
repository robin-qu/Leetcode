// O(n)time O(n)space
class Solution {
    public String addStrings(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }
        
        char[] n1 = num1.toCharArray();
        char[] n2 = num2.toCharArray();
        
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int p1 = n1.length - 1;
        int p2 = n2.length - 1;
        
        while (p1 >= 0 || p2 >= 0) {
            int d1 = p1 >= 0 ? n1[p1] - '0' : 0;
            int d2 = p2 >= 0 ? n2[p2] - '0' : 0;
            sb.insert(0, (carry + d1 + d2) % 10);
            carry = (carry + d1 + d2) / 10;
            p1--;
            p2--;
        }
        
        if (carry == 1) {
            sb.insert(0, '1');
        }
        
        return sb.toString();
    }
}