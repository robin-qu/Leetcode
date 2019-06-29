public class Solution {
    /**
     * @param n: An integer
     * @param times: an array of integers
     * @return: an integer
     */
    public int copyBooksII(int n, int[] times) {
        if (n <= 0 || times == null || times.length == 0) {
            return 0;
        }
        
        int k = times.length;
        int min = Integer.MAX_VALUE;
        for (int time : times) {
            min = Math.min(min, time);
        }
        int left = min;
        int right = n * min;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (getBook(mid, times) < n) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return getBook(left, times) >= n ? left : right;
    }
    
    private int getBook(int time, int[] times) {
        int res = 0;
        
        for (int t : times) {
            res += time / t;
        }
        
        return res;
    }
}