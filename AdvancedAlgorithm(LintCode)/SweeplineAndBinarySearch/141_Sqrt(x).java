public class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        if (x <= 1) {
            return x;
        }
        
        int left = findInterval(x)[0];
        int right = findInterval(x)[1];
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            if (mid < x / mid) {
                left = mid;
            } else if (mid > x / mid) {
                right = mid;
            } else {
                return mid;
            }
        }
        
        return right > x / right ? left : right;
    }
    
    private int[] findInterval(int x) {
        int right = 1;
        
        while (right < x / right) {
            right *= 2;
        }
        
        return new int[]{right / 2, right};
    }
}