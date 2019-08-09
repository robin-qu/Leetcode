// // DP O(n^2)time O(n)space (TLE)
// class Solution {
//     public int oddEvenJumps(int[] A) {
//         if (A == null || A.length == 0) {
//             return 0;
//         }
        
//         int n = A.length;
//         int res = 0;
//         boolean[] up = new boolean[n];
//         boolean[] down = new boolean[n];
//         up[n - 1] = true;
//         down[n - 1] = true;
//         res++;
        
//         for (int i = n - 2; i >= 0; i--) {
//             // go up
//             int min = Integer.MAX_VALUE;
//             // finds the minimum value that is bigger than A[i]
//             for (int j = i + 1; j < n; j++) {
//                 if (A[j] >= A[i]) {
//                     min = Math.min(min, A[j]);
//                 }
//             }
//             // finds the first value that equals min
//             for (int j = i + 1; j < n; j++) {
//                 if (A[j] == min) {
//                     up[i] = down[j];
//                     break;
//                 }
//             }
            
//             // go down, similar to go up
//             int max = Integer.MIN_VALUE;
//             for (int j = i + 1; j < n; j++) {
//                 if (A[j] <= A[i]) {
//                     max = Math.max(max, A[j]);
//                 }
//             }
//             for (int j = i + 1; j < n; j++) {
//                 if (A[j] == max) {
//                     down[i] = up[j];
//                     break;
//                 }
//             }
            
//             // first step must go up
//             if (up[i]) {
//                 res++;
//             }
//         }
        
//         return res;
//     }
// }


// DP + monotonic stack O(n^2)time O(n)space (TLE)
class Solution {
    public int oddEvenJumps(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int n = A.length;
        int res = 0;
        boolean[] up = new boolean[n];
        boolean[] down = new boolean[n];
        up[n - 1] = true;
        down[n - 1] = true;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(A[n - 1], n - 1);
        res++;
        
        for (int i = n - 2; i >= 0; i--) {
            // go up
            Map.Entry high = map.ceilingEntry(A[i]);
            if (high != null) {
                up[i] = down[(int) high.getValue()];
            }
            
            // go down
            Map.Entry low = map.floorEntry(A[i]);
            if (low != null) {
                down[i] = up[(int) low.getValue()];
            }
             
            map.put(A[i], i);
            
            // first step must go up
            if (up[i]) {
                res++;
            }
        }
        
        return res;
    }
}