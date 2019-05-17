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
        
        int longest = L[0];
        for (int l : L) {
            longest = Math.max(l, longest);
        }
        int left = 1;
        int right = longest;
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            
            int count = getCount(L, mid);
            
            if (count < k) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        if (getCount(L, right) >= k) {
            return right;
        }
        
        if (getCount(L, left) >= k) {
            return left;
        }
        
        return 0;
    }
    
    private int getCount(int[] L, int k) {
        int count = 0;
        for (int l : L) {
            count += l / k;
        }
        return count;
    }
}