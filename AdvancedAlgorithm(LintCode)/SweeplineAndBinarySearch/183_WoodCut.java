public class Solution {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        if (L == null || L.length == 0) {
            return 0;
        }
        
        int left = 1;
        int right = 0;
        for (int l : L) {
            right = Math.max(right, l);
        }
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            int counts = getK(L, mid);
            
            if (counts < k) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        if (getK(L, left) < k) {
            return 0;
        }
        
        return getK(L, right) >= k ? right : left;
    }
    
    private int getK(int[] L, int len) {
        int res = 0;
        
        for (int l : L) {
            res += l / len;
        }
        
        return res;
    }
}