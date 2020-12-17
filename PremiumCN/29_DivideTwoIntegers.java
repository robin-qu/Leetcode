// class Solution {
//     public int divide(int dividend, int divisor) {
//         if (dividend == Integer.MIN_VALUE && divisor == -1) {
//             return Integer.MAX_VALUE;
//         }

//         if (dividend == 0) {
//             return 0;
//         }

//         if (divisor == 1) {
//             return dividend;
//         }

//         if (dividend > 0 && divisor > 0) {
//             return getVal(-dividend, -divisor);
//         }
//         if (dividend < 0 && divisor < 0) {
//             return getVal(dividend, divisor);
//         }
//         if (dividend > 0 && divisor < 0) {
//             return -getVal(-dividend, divisor);
//         }
//         return -getVal(dividend, -divisor);
//     }

//     private int getVal(int dividend, int divisor) {
//         int res = 0;
//         while (divisor >= dividend) {
//             int curr = divisor;
//             int factor = 1;
//             while (curr + curr >= dividend) {
//                 curr += curr;
//                 factor += factor;
//             }
//             dividend -= curr;
//             res += factor;
//         }
//         return res;
//     }
// }


class Solution {
    public int divide(int dividend1, int divisor1) {
        if (dividend1 == 0) {
            return 0;
        }
        
        if (dividend1 == Integer.MIN_VALUE && divisor1 == -1) {
            return Integer.MAX_VALUE;
        }
        
        int sign = 1;
        if (dividend1 > 0 && divisor1 < 0 || dividend1 < 0 && divisor1 > 0) {
            sign = -1;
        }
        
        long dividend = Math.abs((long) dividend1);
        long divisor = Math.abs((long) divisor1);
        
        int res = 0;
        
        while (dividend >= divisor) {
            long curr = divisor;
            int count = 1;
            while (dividend >= curr + curr) {
                curr += curr;
                count += count;
            }
            
            dividend -= curr;
            res += count;
        }
        
        return sign * res;
    }
}