public class Solution {
    /**
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        if (A == null || A.length == 0) {
            return B;
        }
        
        if (B == null || B.length == 0) {
            return A;
        }
        
        int lenA = A.length;
        int lenB = B.length;
        int[] res = new int[lenA + lenB];
        int a = 0;
        int b = 0;
        int i = 0;
        
        while (a < lenA && b < lenB) {
            if (A[a] < B[b]) {
                res[i++] = A[a++];
            } else {
                res[i++] = B[b++];
            }
        }
        
        while (a < lenA) {
            res[i++] = A[a++];
        }
        
        while (b < lenB) {
            res[i++] = B[b++];
        }
        
        return res;
    }
}