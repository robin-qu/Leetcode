public class Solution {
    /**
     * @param x: a double
     * @return: the square root of x
     */
    public double sqrt(double x) {
        if (x < 0) {
            return -1;
        }
        
        double left;
        double right;
        double mid;
        
        if (x < 1.0) {
            left = x;
            right = 1.0;
        } else if (x > 1.0) {
            left = 1.0;
            right = x;
        } else {
            return 1.0;
        }
        
        while (Math.abs(left - right) > 1e-12) {
            mid = left + (right - left) / 2;
            
            if (mid < x / mid) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
}