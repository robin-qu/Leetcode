// class Solution {
//     public static final int STONE = '1';
//     public static final int WATER = '0';
    
//     class Coor {
//         public int x;
//         public int y;
        
//         public Coor(int x, int y) {
//             this.x = x;
//             this.y = y;
//         }
//     }
    
//     public int numIslands(char[][] grid) {
//         if (grid == null || grid.length == 0 || grid[0].length == 0) {
//             return 0;
//         }
        
//         int m = grid.length;
//         int n = grid[0].length;
//         int count = 0;
//         boolean[][] visited = new boolean[m][n];
        
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (!visited[i][j]) {
//                     visited[i][j] = true;
//                     if (grid[i][j] == STONE) {
//                         count++;
//                         bfs(new Coor(i, j), grid, visited);
//                     }
//                 }
//             }
//         }
//         return count;
//     }
    
//     private void bfs(Coor origin, char[][] grid, boolean[][] visited) {
//         Queue<Coor> queue = new LinkedList<>();
//         queue.offer(origin);
        
//         int[] dX = {-1, 0, 0, 1};
//         int[] dY = {0, 1, -1, 0};
        
//         while (!queue.isEmpty()) {
//             Coor curr = queue.poll();
            
//             for (int i = 0; i < 4; i++) {
//                 Coor newPos = new Coor(curr.x + dX[i], curr.y + dY[i]);
//                 if (inBound(newPos, grid) && !visited[newPos.x][newPos.y]) {
//                     visited[newPos.x][newPos.y] = true;
//                     if (grid[newPos.x][newPos.y] == STONE) {
//                         queue.offer(newPos);
//                     }
//                 }
//             }
//         }
        
//     }
    
//     private boolean inBound(Coor pos, char[][] grid) {
//         int m = grid.length;
//         int n = grid[0].length;
//         return pos.x >= 0 && pos.x < m && pos.y >= 0 && pos.y < n;
//     }
// }

// Without using visited matrix
class Solution {
    public static final int STONE = '1';
    public static final int WATER = '0';
    
    class Coor {
        public int x;
        public int y;
        
        public Coor(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == STONE) {
                    count++;
                    bfs(new Coor(i, j), grid);
                }
            }
        }
        return count;
    }
    
    private void bfs(Coor origin, char[][] grid) {
        Queue<Coor> queue = new LinkedList<>();
        queue.offer(origin);
        
        int[] dX = {-1, 0, 0, 1};
        int[] dY = {0, 1, -1, 0};
        
        while (!queue.isEmpty()) {
            Coor curr = queue.poll();
            grid[curr.x][curr.y] = WATER;
            
            for (int i = 0; i < 4; i++) {
                Coor newPos = new Coor(curr.x + dX[i], curr.y + dY[i]);
                if (inBound(newPos, grid) && grid[newPos.x][newPos.y] == STONE) {
                    grid[newPos.x][newPos.y] = WATER;
                    queue.offer(newPos);
                }
            }
        }
        
    }
    
    private boolean inBound(Coor pos, char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return pos.x >= 0 && pos.x < m && pos.y >= 0 && pos.y < n;
    }
}