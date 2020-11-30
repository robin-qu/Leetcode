class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        s = s.trim();
        if (s.isEmpty()) {
            return 0;
        }
        char[] ss = s.toCharArray();

        int res = 0;
        int i = 0;
        if (ss[0] == '-') {
            i++;
            while (i < ss.length && Character.isDigit(ss[i])) {
                if (res < (Integer.MIN_VALUE + (ss[i] - '0')) / 10) {
                    return Integer.MIN_VALUE;
                }
                res = res * 10 - (ss[i++] - '0');
            }
        } else {
            if (ss[0] == '+') {
                i++;
            }
            while (i < ss.length && Character.isDigit(ss[i])) {
                if (res > (Integer.MAX_VALUE - (ss[i] - '0')) / 10) {
                    return Integer.MAX_VALUE;
                }
                res = res * 10 + (ss[i++] - '0');
            }
        }
        
        return res;
    }
}