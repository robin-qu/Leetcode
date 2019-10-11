// // dfs O(2^(tx + ty))time O(tx + ty)space
// class Solution {
//     public boolean reachingPoints(int sx, int sy, int tx, int ty) {
//         if (sx == tx && sy == ty) {
//             return true;
//         }
        
//         if (sx > tx || sy > ty) {
//             return false;
//         }
        
//         return reachingPoints(sx, sx + sy, tx, ty) || reachingPoints(sx + sy, sy, tx, ty);
//     }
// }


// // dfs with memo O(txty)time O(txty)space
// class Solution {
//     public boolean reachingPoints(int sx, int sy, int tx, int ty) {
//         int[][] seen = new int[tx][ty];
        
//         return search(sx, sy, tx, ty, seen);
//     }
        
//     private boolean search(int sx, int sy, int tx, int ty, int[][] seen) {
//         if (seen[tx - 1][ty - 1] != 0) {
//             return seen[tx - 1][ty - 1] == 1 ? true : false;
//         }
        
//         if (sx == tx && sy == ty) {
//             seen[tx - 1][ty - 1] = 1;
//             return true;
//         }
        
//         if (sx > tx || sy > ty) {
//             seen[tx - 1][ty - 1] = -1;
//             return false;
//         }
        
//         if (reachingPoints(sx, sx + sy, tx, ty) || reachingPoints(sx + sy, sy, tx, ty)) {
//             seen[tx - 1][ty - 1] = 1;
//             return true;
//         }
        
//         seen[tx - 1][ty - 1] = -1;
//         return false;
//     }
// }


// iterative O(log(max(tx, ty)))time O(1)space
class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (sx < tx && sy < ty) {
            if (tx < ty) {
                ty %= tx;
            } else {
                tx %= ty;
            }
        }
        
        return sx == tx && sy <= ty && (ty - sy) % sx == 0 ||
               sy == ty && sx <= tx && (tx - sx) % sy == 0;
    }
}
