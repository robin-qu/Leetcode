// // dfs O9mn)time O(mn)space
// class Solution {
//     private static final int[] dx = new int[]{0, 1, -1, 0};
//     private static final int[] dy = new int[]{1, 0, 0, -1};
    
//     public boolean hasPath(int[][] maze, int[] start, int[] destination) {
//         if (maze == null || maze.length == 0 ||
//             maze[0] == null || maze[0].length == 0) {
//             return false;
//         }
        
//         int m = maze.length;
//         int n = maze[0].length;
//         boolean[][] reachable = new boolean[m][n];
//         reachable[start[0]][start[1]] = true;
        
//         dfs(start[0], start[1], destination, maze, reachable);
        
//         return reachable[destination[0]][destination[1]];
//     }
    
//     private void dfs(int x, int y, int[] destination, int[][] maze, boolean[][] reachable) {
//         if (x == destination[0] && y == destination[1]) {
//             reachable[x][y] = true;
//             return;
//         }
        
//         reachable[x][y] = true;
        
//         for (int i = 0; i < 4; i++) {
//             int nx = x;
//             int ny = y;
            
//             while (valid(nx + dx[i], ny + dy[i], maze)) {
//                 nx += dx[i];
//                 ny += dy[i];
//             }
            
//             if (!reachable[nx][ny]) {
//                 dfs(nx, ny, destination, maze, reachable);
//             }
//         }
//     }
    
//     private boolean valid(int x, int y, int[][] maze) {
//         return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
//     }
// }


// bfs O(mn)time O(mn)space
class Solution {
    private static final int[] dx = new int[]{0, 1, -1, 0};
    private static final int[] dy = new int[]{1, 0, 0, -1};
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 ||
            maze[0] == null || maze[0].length == 0) {
            return false;
        }
        
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] reachable = new boolean[m][n];
        reachable[start[0]][start[1]] = true;
        
        bfs(start, maze, reachable);
        
        return reachable[destination[0]][destination[1]];
    }
    
    private void bfs(int[] start, int[][] maze, boolean[][] reachable) {
        int m = maze.length;
        int n = maze[0].length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start[0] * n + start[1]);
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int x = curr / n;
            int y = curr % n;
            reachable[x][y] = true;
            
            for (int i = 0; i < 4; i++) {
                int nx = x;
                int ny = y;

                while (valid(nx + dx[i], ny + dy[i], maze)) {
                    nx += dx[i];
                    ny += dy[i];
                }

                if (!reachable[nx][ny]) {
                    queue.offer(nx * n + ny);
                }
            }
        }
    }
    
    private boolean valid(int x, int y, int[][] maze) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
    }
}