// O(n)time O(1)space
class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        if (A == null || B == null || A.length != B.length) {
            return -1;
        }
        
        int n = A.length;
        
        int a1 = 0;
        int b1 = 0;
        int val1 = A[0];
        for (int i = 0; i < n; i++) {
            if (A[i] != val1 && B[i] != val1) {
                a1 = -1;
                break;
            }
            
            if (A[i] != val1) {
                a1++;
            } else if (B[i] != val1) {
                b1++;
            }
        }
        if (a1 != -1) {
            return Math.min(a1, b1);
        }
        
        int a2 = 0;
        int b2 = 0;
        int val2 = B[0];
        for (int i = 0; i < n; i++) {
            if (A[i] != val2 && B[i] != val2) {
                a2 = -1;
                break;
            }
            
            if (A[i] != val2) {
                a2++;
            } else if (B[i] != val2) {
                b2++;
            }
        }
        if (a2 != -1) {
            return Math.min(a2, b2);
        }
        
        return -1;
    }
}