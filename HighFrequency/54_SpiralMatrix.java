// // O(mn)time O(mn)space
// class Solution {
//     public static final int[] dx = new int[]{0, 1, 0, -1};
//     public static final int[] dy = new int[]{1, 0, -1, 0};
    
//     public List<Integer> spiralOrder(int[][] matrix) {
//         if (matrix == null || matrix.length == 0 ||
//             matrix[0] == null || matrix[0].length == 0) {
//             return new ArrayList<>();
//         }
        
//         int m = matrix.length;
//         int n = matrix[0].length;
//         boolean[][] visited = new boolean[m][n];
//         List<Integer> res = new ArrayList<>();
        
//         int x = 0;
//         int y = 0;
//         int lastMove = 0;
//         for (int i = 0; i < m * n; i++) {
//             res.add(matrix[x][y]);
//             visited[x][y] = true;
            
//             int nx = x + dx[lastMove];
//             int ny = y + dy[lastMove];
            
//             // if the next position in last move's direction is valid, keep moving 
//             if (inBound(nx, ny, m, n) && !visited[nx][ny]) {
//                 x = nx;
//                 y = ny;
//                 continue;
//             }
            
//             for (int j = 0; j < 4; j++) {  // right -> down -> keft -> up
//                 if (j == lastMove) {
//                     continue;  // skip invalid last move
//                 }
                
//                 nx = x + dx[j];
//                 ny = y + dy[j];
                
//                 if (inBound(nx, ny, m, n) && !visited[nx][ny]) {
//                     x = nx;
//                     y = ny;
//                     lastMove = j;  // update the direction information
//                     break;
//                 }
//             }
//         }
        
//         return res;
//     }
    
//     private boolean inBound(int x, int y, int m, int n) {
//         return x >= 0 && x < m && y >= 0 && y < n;
//     }
// }



// O(mn)time O(mn)space
class Solution {
    public static final int[] dx = new int[]{0, 1, 0, -1};
    public static final int[] dy = new int[]{1, 0, -1, 0};
    
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        
        // dfs(0, 0, matrix, visited, res);
        int rowBegin = 0;
        int rowEnd = m - 1;
        int colBegin = 0;
        int colEnd = n - 1;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // right
            for (int i = colBegin; i <= colEnd; i++) {
                res.add(matrix[rowBegin][i]);
            }
            rowBegin++;
            
            // down
            for (int i = rowBegin; i <= rowEnd; i++) {
                res.add(matrix[i][colEnd]);
            }
            colEnd--;
            
            // left
            if (rowBegin > rowEnd) {
                break;  // check if row still exists
            }
            for (int i = colEnd; i >= colBegin; i--) {
                res.add(matrix[rowEnd][i]);
            }
            rowEnd--;
            
            // up
            if (colBegin > colEnd) {
                break;  // check if col still exists
            }
            for (int i = rowEnd; i >= rowBegin; i--) {
                res.add(matrix[i][colBegin]);
            }
            colBegin++;
        }
        
        return res;
    }
}