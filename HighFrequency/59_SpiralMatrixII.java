// // O(n^2)time O(n^2)space
// class Solution {
//     private static final int[] DX = new int[]{0, 1, 0, -1};  // right -> down -
//     private static final int[] DY = new int[]{1, 0, -1, 0};  // left -> up
    
//     public int[][] generateMatrix(int n) {
//         if (n <= 0) {
//             return new int[0][0];
//         }
        
//         int[][] res = new int[n][n];
        
//         int num = 1;
//         int prev = 0;  // initial direction is right
//         int x = 0;
//         int y = 0;
        
//         while (num <= n * n) {
//             res[x][y] = num;
            
//             if (!inBound(x + DX[prev], y + DY[prev], n) || res[x + DX[prev]][y + DY[prev]] != 0) {  // cannot continue moving
//                 prev = (prev + 1) % 4;  // change direction
//             }
            
//             x += DX[prev];
//             y += DY[prev];
            
//             num++;
//         }
        
//         return res;
//     }
    
//     private boolean inBound(int x, int y, int n) {
//         return x >= 0 && x < n && y >= 0 && y < n;
//     }
// }


// O(n^2)time O(n^2)space
class Solution {
    public int[][] generateMatrix(int n) {
        if (n <= 0) {
            return new int[0][0];
        }
        
        int[][] res = new int[n][n];
        
        int r0 = 0;  // first valid row
        int rn = n - 1;  // last valid row
        int c0 = 0;  // first valid column
        int cn = n - 1;  // last valid row
        int num = 1;
        
        while (r0 <= rn || c0 <= cn) {
            for (int i = c0; i <= cn; i++) {
                res[r0][i] = num++;
            }
            r0++;
            
            for (int i = r0; i <= rn; i++) {
                res[i][cn] = num++;
            }
            cn--;
            
            if (cn < c0) {
                break;
            }
            for (int i = cn; i >= c0; i--) {
                res[rn][i] = num++;
            }
            rn--;
            
            if (rn <c0) {
                break;
            }
            for (int i = rn; i >= r0; i--) {
                res[i][c0] = num++;
            }
            c0++;
        }
        
        return res;
    }
}