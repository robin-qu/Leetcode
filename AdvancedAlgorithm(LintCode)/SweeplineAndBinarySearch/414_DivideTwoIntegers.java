public class Solution {
    /**
     * @param dividend: the dividend
     * @param divisor: the divisor
     * @return: the result
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return dividend > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        
        // handle special overflow
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        boolean isNegative = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        
        long longDividend = Math.abs((long) dividend);
        long longDivisor = Math.abs((long) divisor);
        int res = 0;
        
        while (longDividend >= longDivisor) {
            int shift = 0;
            while (longDividend >= (longDivisor << shift)) {
                shift++;
            }
            
            longDividend -= longDivisor << (shift - 1);
            res += 1 << (shift - 1);
        }
        
        return isNegative ? -res : res;
    }
}