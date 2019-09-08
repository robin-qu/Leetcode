// // dfs O(4^(mn))time O(mn)space (TLE)
// class Solution {
//     private static final int[] dx = new int[]{0, 1, -1, 0};
//     private static final int[] dy = new int[]{1, 0, 0, -1};
    
//     public int longestIncreasingPath(int[][] matrix) {
//         if (matrix == null || matrix.length == 0 ||
//             matrix[0] == null || matrix[0].length == 0) {
//             return 0;
//         }
        
//         int m = matrix.length;
//         int n = matrix[0].length;
        
//         int max = 0;
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 boolean[][] visited = new boolean[m][n];
//                 max = Math.max(max, dfs(i, j, matrix, 1, visited));
//             }
//         }
        
//         return max;
//     }
    
//     private int dfs(int x, int y, int[][] matrix, int len, boolean[][] visited) {
//         int m = matrix.length;
//         int n = matrix[0].length;
//         visited[x][y] = true;
//         int max = len;
        
//         for (int i = 0; i < 4; i++) {
//             int nx = x + dx[i];
//             int ny = y + dy[i];
            
//             if (inBound(nx, ny, m, n) && !visited[nx][ny] && matrix[nx][ny] > matrix[x][y]) {
//                 max = Math.max(max, dfs(nx, ny, matrix, len + 1, visited));
//             }
//         }
        
//         visited[x][y] = false;
        
//         return max;
//     }
    
//     private boolean inBound(int x, int y, int X, int Y) {
//         return x >= 0 && x < X && y >= 0 && y < Y;
//     }
// }


// // dfs with memo O(mn)time O(mn)space
// // do not need a visited array since there is a constrain matrix[x][y] <= matrix[nx][ny]
// class Solution {
//     private static final int[] dx = new int[]{0, 1, -1, 0};
//     private static final int[] dy = new int[]{1, 0, 0, -1};
    
//     public int longestIncreasingPath(int[][] matrix) {
//         if (matrix == null || matrix.length == 0 ||
//             matrix[0] == null || matrix[0].length == 0) {
//             return 0;
//         }
        
//         int m = matrix.length;
//         int n = matrix[0].length;
//         int[][] memo = new int[m][n];  // the longest increasing length started at (m, n)
        
//         int max = 1;
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 max = Math.max(max, dfs(i, j, matrix, memo));
//             }
//         }
        
//         return max;
//     }
    
//     private int dfs(int x, int y, int[][] matrix, int[][] memo) {
//         if (memo[x][y] > 0) {
//             return memo[x][y];
//         }
        
//         int m = matrix.length;
//         int n = matrix[0].length;
        
//         int max = 1;
//         for (int i = 0; i < 4; i++) {
//             int nx = x + dx[i];
//             int ny = y + dy[i];
            
//             if (inBound(nx, ny, m, n) && matrix[nx][ny] > matrix[x][y]) {
//                 max = Math.max(max, 1 + dfs(nx, ny, matrix, memo));
//             }
//         }
        
//         memo[x][y] = max;
//         return max;
//     }
    
//     private boolean inBound(int x, int y, int X, int Y) {
//         return x >= 0 && x < X && y >= 0 && y < Y;
//     }
// }


// pq O(mnlogmn)time O(mn)space
class Solution {
    private static final int[] dx = new int[]{0, 1, -1, 0};
    private static final int[] dy = new int[]{1, 0, 0, -1};
    
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return b[2] - a[2];
            }
        });
        
        int[][] lens = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                lens[i][j] = 1;
                pq.offer(new int[]{i, j, matrix[i][j]});
            }
        }
        
        int max = 1;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            
            int x = curr[0];
            int y = curr[1];
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (inBound(nx, ny, m, n) && matrix[nx][ny] > matrix[x][y]) {
                    lens[x][y] = Math.max(lens[x][y],  1 + lens[nx][ny]);
                }
            }
            
            max = Math.max(max, lens[x][y]);
        }
        
        return max;
    }
    
    private boolean inBound(int x, int y, int X, int Y) {
        return x >= 0 && x < X && y >= 0 && y < Y;
    }
}