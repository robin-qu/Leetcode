class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        
        char[] ss = str.toCharArray();
        int n = ss.length;
        int res = 0;
        int i = 0;
        int sign = 1;
        
        // skip whitespace
        while (i < n && ss[i] == ' ') {
            i++;
        }
        
        // record optional sign
        if (i < n && ss[i] == '+') {
            i++;
        } else if (i < n && ss[i] == '-') {
            sign = -1;
            i++;
        }
        
        // get rid of letters
        if (i >= n || !Character.isDigit(ss[i])) {
            return 0;
        }
        
        while (i < n && Character.isDigit(ss[i])) {
            // deal with overflow
            if (res > Integer.MAX_VALUE / 10) {
                if (sign > 0) {
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.MIN_VALUE;
                }
            }
            
            if (res * 10 > Integer.MAX_VALUE - (ss[i] - '0')) {
                if (sign > 0) {
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.MIN_VALUE;
                }
            }
            
            res = res * 10 + (ss[i] - '0');
            i++;
        }
        
        return sign * res;
    }
}