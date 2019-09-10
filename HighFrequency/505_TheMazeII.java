// // dfs O(4^(mn))time O(mn)space
// class Solution {
//     private static final int[] dx = new int[]{0, 1, -1, 0};
//     private static final int[] dy = new int[]{1, 0, 0, -1};
    
//     public int shortestDistance(int[][] maze, int[] start, int[] destination) {
//         if (maze == null || maze.length == 0 ||
//             maze[0] == null || maze[0].length == 0) {
//             return 0;
//         }
        
//         int m = maze.length;
//         int n = maze[0].length;
        
//         int[][] dists = new int[m][n];  // the minimum distance to reach this (i, j) from the start position
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 dists[i][j] = Integer.MAX_VALUE;
//             }
//         }
//         dists[start[0]][start[1]] = 0;
        
//         dfs(start[0], start[1], destination, maze, dists);
        
//         return dists[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dists[destination[0]][destination[1]];
//     }
    
//     private void dfs(int x, int y, int[] destination, int[][] maze, int[][] dists) {
//         if (x == destination[0] && y == destination[1]) {  // reach the dest
//             return;
//         }
        
//         for (int i = 0; i < 4; i++) {            
//             int nx = x;
//             int ny = y;
//             int count = 0;
//             while (isValid(nx + dx[i], ny + dy[i], maze)) {  // can still move in i'th direction
//                 nx += dx[i];
//                 ny += dy[i];
//                 count++;
//             }
            
//             if (dists[x][y] + count >= dists[nx][ny]) {
//                 continue;
//             }
            
//             dists[nx][ny] = dists[x][y] + count;
//             dfs(nx, ny, destination, maze, dists);
//         }
//     }
    
//     private boolean isValid(int x, int y, int[][] maze) {
//         return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
//     }
// }


// bfs O(4(mn))time O(mn)space
class Solution {
    private static final int[] dx = new int[]{0, 1, -1, 0};
    private static final int[] dy = new int[]{1, 0, 0, -1};
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 ||
            maze[0] == null || maze[0].length == 0) {
            return 0;
        }
        
        int m = maze.length;
        int n = maze[0].length;
        
        int[][] dists = new int[m][n];  // the minimum distance to reach this (i, j) from the start position
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dists[i][j] = Integer.MAX_VALUE;
            }
        }
        dists[start[0]][start[1]] = 0;
        
        bfs(start[0], start[1], maze, dists);
        
        return dists[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dists[destination[0]][destination[1]];
    }
    
    private void bfs(int x, int y, int[][] maze, int[][] dists) {
        int m = maze.length;
        int n = maze[0].length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x * n + y);
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int currX = curr / n;
            int currY = curr % n;
            
            for (int i = 0; i < 4; i++) {            
                int nx = currX;
                int ny = currY;
                int count = 0;
                while (isValid(nx + dx[i], ny + dy[i], maze)) {  // can still move in i'th direction
                    nx += dx[i];
                    ny += dy[i];
                    count++;
                }

                if (dists[currX][currY] + count < dists[nx][ny]) {
                    dists[nx][ny] = dists[currX][currY] + count;
                    queue.offer(nx * n + ny);
                }
            }
        }
    }
    
    private boolean isValid(int x, int y, int[][] maze) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
    }
}



// // Dijkstra's O(mnlogmn)time O(mn)space
// class Solution {
//     private static final int[] dx = new int[]{0, 1, -1, 0};
//     private static final int[] dy = new int[]{1, 0, 0, -1};
    
//     public int shortestDistance(int[][] maze, int[] start, int[] destination) {
//         if (maze == null || maze.length == 0 ||
//             maze[0] == null || maze[0].length == 0) {
//             return 0;
//         }
        
//         int m = maze.length;
//         int n = maze[0].length;
        
//         int[][] dists = new int[m][n];  // the minimum distance to reach this (i, j) from the start position
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 dists[i][j] = Integer.MAX_VALUE;
//             }
//         }
//         dists[start[0]][start[1]] = 0;
        
//         dijkstra(start, destination, maze, dists);
        
//         return dists[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dists[destination[0]][destination[1]];
//     }
    
//     private void dijkstra(int[] start, int[] destination, int[][] maze, int[][] dists) {
//         int m = maze.length;
//         int n = maze[0].length;
//         PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
//             @Override
//             public int compare(int[] a, int[] b) {
//                 return a[2] - b[2];
//             }
//         });
//         pq.offer(new int[]{start[0], start[1], 0});
        
//         while (!pq.isEmpty()) {
//             int[] curr = pq.poll();
//             int x = curr[0];
//             int y = curr[1];
            
//             if (dists[x][y] < curr[2]) {  // do not need to update
//                 continue;
//             }
            
//             for (int i = 0; i < 4; i++) {            
//                 int nx = x;
//                 int ny = y;
//                 int count = 0;
//                 while (isValid(nx + dx[i], ny + dy[i], maze)) {  // can still move in i'th direction
//                     nx += dx[i];
//                     ny += dy[i];
//                     count++;
//                 }
                
//                 dists[nx][ny] = dists[x][y] + count;
//                 pq.offer(new int[]{nx, ny, dists[nx][ny]});
//             }
            
//             if (x == destination[0] && y == destination[1]) {  // destination,  can terminate it
//                 return;
//             }
//         }
//     }
    
//     private boolean isValid(int x, int y, int[][] maze) {
//         return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
//     }
// }