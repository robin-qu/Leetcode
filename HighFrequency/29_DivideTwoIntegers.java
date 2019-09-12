class Solution {
    public int divide(int dividend, int divisor) {
        int sign = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0) ? 1 : -1;
        
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        
        long res = compute(a, b);
        
        if (res > Integer.MAX_VALUE) {
            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        
        return sign * (int) res;
    }
    
    private long compute(long a, long b) {
        if (a < b) {
            return 0;
        }
        
        long sum = b;        
        long count = 1;
        long res = 0;
        
        while (a >= sum) {
            res += count;
            a -= sum;
            sum += sum;
            count += count;
        }
        
        return res + compute(a, b);
    }
}