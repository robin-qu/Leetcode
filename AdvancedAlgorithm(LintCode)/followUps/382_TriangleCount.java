// public class Solution {
//     /**
//      * @param S: A list of integers
//      * @return: An integer
//      */
//     public int triangleCount(int[] S) {
//         if (S == null || S.length == 0) {
//             return 0;
//         }
        
//         Arrays.sort(S);
//         int res = 0;
        
//         for (int i = 0; i < S.length - 2; i++) {
//             res += findTriangle(S, S[i], i + 1);
//         }
        
//         return res;
//     }
    
//     // given the first edge, finds the number of triangles
//     // right point as main pointer
//     private int findTriangle(int[] S, int edge, int start) {
//         int left = start;
//         int res = 0;
        
//         for (int right = start + 1; right < S.length; right++) {
//             while (left < right && S[right] - S[left] >= edge) {
//                 left++;
//             }
            
//             res += right - left;
//         }
        
//         return res;
//     }
// }

// public class Solution {
//     /**
//      * @param S: A list of integers
//      * @return: An integer
//      */
//     public int triangleCount(int[] S) {
//         if (S == null || S.length == 0) {
//             return 0;
//         }
        
//         Arrays.sort(S);
//         int res = 0;
        
//         for (int i = 0; i < S.length - 2; i++) {
//             res += findTriangle(S, S[i], i + 1);
//         }
        
//         return res;
//     }
    
//     // given the first edge, finds the number of triangles
//     // left pointer as main pointer
//     private int findTriangle(int[] S, int edge, int start) {
//         int right = start + 1;
//         int res = 0;
        
//         for (int left = start; left < S.length - 1; left++) {
//             while (right < S.length && S[right] - S[left] < edge) {
//                 right++;
//             }
            
//             res += right - left - 1;
//         }
        
//         return res;
//     }
// }


public class Solution {
    /**
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int[] S) {
        if (S == null || S.length == 0) {
            return 0;
        }
        
        Arrays.sort(S);
        int res = 0;
        
        for (int i = 0; i < S.length - 2; i++) {
            res += findTriangle(S, S[i], i + 1);
        }
        
        return res;
    }
    
    // given the first edge, finds the number of triangles
    // two pointers
    private int findTriangle(int[] S, int edge, int start) {
        int left = start;
        int right = start;
        int res = 0;
        
        while (left <= right && right < S.length) {
            if (S[right] - S[left] < edge) {
                right++;
                res += right - left - 1;
            } else {
                left++;
            }
        }
        
        return res;
    }
}