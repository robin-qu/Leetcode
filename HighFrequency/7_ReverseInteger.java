class Solution {
    public int reverse(int x) {
        int res = 0;
        int xAbs = Math.abs(x);
        
        while (xAbs > 0) {
            int digit = xAbs % 10;
            if ((Integer.MAX_VALUE - digit) / 10 < res) {
                return 0;
            }
            res = res * 10 + digit;
            xAbs /= 10;
        }
        
        return x >= 0 ? res : -res;
    }
}