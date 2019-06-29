public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = 0;
        for (int page : pages) {
            left = Math.max(left, page);
            right += page;
        }
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            if (getK(pages, mid) > k) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return getK(pages, left) <= k ? left : right;
    }
    
    private int getK(int[] pages, int time) {
        int res = 1;
        int count = 0;
        for (int page : pages) {
            if (count + page > time) {
                res++;
                count = 0;
            }
            count += page;
        }
        
        return res;
    }
}