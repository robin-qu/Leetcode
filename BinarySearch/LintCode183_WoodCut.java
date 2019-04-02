/*
Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.

Example
For L=[232, 124, 456], k=7, return 114.

Challenge
O(n log Len), where Len is the longest length of the wood.

Notice
You couldn't cut wood into float length.

If you couldn't get >= k pieces, return 0.
*/
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
        
        int max = 0;
        for (int l : L) {
            if (l > max) {
                max = l;
            }
        }
        
        int low = 1;
        int high = max;
        
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (getK(L, mid) < k) {
                high = mid;
            } else {
                low = mid;
            }
        }
        
        if (getK(L, high) >= k) {
            return high;
        }
        if (getK(L, low) >= k) {
            return low;
        }
        return 0;
    }
    
    
    // Returns the number of wood pieces given the length len
    private int getK(int[] L, int len) {
        int res = 0;
        for (int l : L) {
            res += l / len;
        }
        return res;
    }
}