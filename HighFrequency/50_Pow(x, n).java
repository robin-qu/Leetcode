// // brute force O(n)time O(1)space
// class Solution {
//     public double myPow(double x, int n) {
//         double res = 1.0;
//         boolean isNegative = n < 0;
        
//         for (int i = 0; i < Math.abs(n); i++) {
//             res *= x;
//         }
        
//         if (isNegative) {
//             return 1.0 / res;
//         }
        
//         return res;
//     }
// }

// // O(n)time O(1)space (TLE)
// class Solution {
//     public double myPow(double x, int N) {
//         double res = 1.0;
//         boolean isNegative = N < 0;
//         long n = (long) N;
//         if (n < 0) {
//             n = -n;
//         }
        
//         while (n > 0) {
//             double local = x;
//             int pow = 1;
//             while (n - pow * 2 >= 0) {
//                 local *= local;
//                 pow *= 2;
//             }
//             n -= pow;
//             res *= local;
//         }
        
//         if (isNegative) {
//             return 1.0 / res;
//         }
        
//         return res;
//     }
// }

// BinarySearch O(logn)time O(1)space
public class Solution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        
        if (n == Integer.MIN_VALUE) {
            x *= x;
            n /= 2;
        }
        
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        
        if (n % 2 == 1) {
            return x * myPow(x * x, n / 2);
        }
        
        return myPow(x * x, n / 2);
    }
}