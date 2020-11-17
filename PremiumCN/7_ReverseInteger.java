class Solution {
    public int reverse(int x) {
        int sign = x >= 0 ? 1 : -1;
        x = Math.abs(x);
        int res = 0;
        while (x > 0) {
            int digit = x % 10;
            x /= 10;
            if ((Integer.MAX_VALUE - digit) / 10 < res) {
                return 0;
            }
            res = res * 10 + digit;
        }
        
        
        return sign * res;
    }
}