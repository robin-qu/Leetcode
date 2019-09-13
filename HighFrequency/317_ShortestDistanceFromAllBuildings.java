// bfs O(mmnn)time O(mn)space
class Solution {
    private static final int EMPTY = 0;
    private static final int BUILDING = 1;
    private static final int OBSTACLE = 2;
    private static final int[] dx = new int[]{0, 1, -1, 0};
    private static final int[] dy = new int[]{1, 0, 0, -1};
    
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 ||
            grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] dists = new int[m][n];
        int[][] counts = new int[m][n];
        
        int bCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == BUILDING) {
                    bCount++;
                    bfs(i, j, grid, dists, counts);
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == EMPTY && counts[i][j] == bCount) {
                    min = Math.min(min, dists[i][j]);
                }
            }
        }
        
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    private void bfs(int x, int y, int[][] grid, int[][] dists, int[][] counts) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[x][y] = true;
        int dist = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int currX = curr[0];
                int currY = curr[1];
                
                dists[currX][currY] += dist;
                counts[currX][currY]++;
                
                for (int k = 0; k < 4; k++) {
                    int nx = currX + dx[k];
                    int ny = currY + dy[k];
                    
                    if (inBound(nx, ny, grid) && grid[nx][ny] == EMPTY && !visited[nx][ny]) {
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            
            dist++;
        }
    }
    
    private boolean inBound(int x, int y, int[][] grid) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length;
    }
}