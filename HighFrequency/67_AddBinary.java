class Solution {
    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0 || b == null || b.length() == 0) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();
        int ia = a.length() - 1;
        int ib = b.length() - 1;
        int carry = 0;
        
        while (ia >= 0 || ib >= 0) {
            int first = ia >= 0 ? aa[ia] - '0' : 0;
            int second = ib >= 0 ? bb[ib] - '0' : 0;
            int curr = carry + first + second;
            
            if (curr <= 1) {
                carry = 0;
            } else {
                carry = 1;
            }
            
            sb.insert(0, curr % 2);
            
            ia--;
            ib--;
        }
        
        if (carry == 1) {
            sb.insert(0, '1');
        }
        
        return sb.toString();
    }
}