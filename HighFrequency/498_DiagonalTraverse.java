// // O(mn)time O(1)space
// class Solution {
//     public int[] findDiagonalOrder(int[][] matrix) {
//         if (matrix == null || matrix.length == 0 ||
//             matrix[0] == null || matrix[0].length == 0) {
//             return new int[0];
//         }
        
//         int m = matrix.length;
//         int n = matrix[0].length;
//         int[] res = new int[m * n];
//         int idx = 0;
        
//         for (int i = 1; i < m + n; i++) {
//             if (i % 2 == 1) {
//                 int x = (i <= m ? i - 1 : m - 1);
//                 int y = (i <= m ? 0 : i - m);
//                 while (x >= 0 && y < n) {
//                     res[idx++] = matrix[x--][y++];
//                 }
//             } else {
//                 int x = (i <= n ? 0 : i - n);
//                 int y = (i <= n ? i - 1 : n - 1);
//                 while (x < m && y >= 0) {
//                     res[idx++] = matrix[x++][y--];
//                 }
//             }
//         }
        
//         return res;
//     }
// }


// O(mn)time O(1)space
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return new int[0];
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[] res = new int[m * n];
        int x = 0;
        int y = 0;
        
        for (int idx = 0; idx < m * n; idx++) {
            res[idx] = matrix[x][y];
            
            if ((x + y) % 2 == 0) {  // going up
                if (y == n - 1) {  // reach right boundary
                    x++;
                } else if (x == 0) {  // reach top boundary
                    y++;
                } else {
                    x--;
                    y++;
                }
            } else {  // going down
                if (x == m - 1) {  // reach bottom boundary
                    y++;
                } else if (y == 0) {  // reach left boundary
                    x++;
                } else {
                    x++;
                    y--;
                }
            }
        }
        
        return res;
    }
}