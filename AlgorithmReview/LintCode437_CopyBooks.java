public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0 || k <= 0) {
            return 0;
        }
        
        int max = 0;
        int sum = 0;
        for (int page : pages) {
            max = Math.max(max, page);
            sum += page;
        }
        
        int left = max;
        int right = sum;
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            if (getK(pages, mid) > k) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        if (getK(pages, left) <= k) {
            return left;
        }
        return right;
    }
    
    private int getK(int[] pages, int max) {
        int k = 1;
        int sum = 0;
        for (int page : pages) {
            if (sum + page <= max) {
                sum += page;
            } else {
                k++;
                sum = page;
            }
        }
        return k;
    }
}