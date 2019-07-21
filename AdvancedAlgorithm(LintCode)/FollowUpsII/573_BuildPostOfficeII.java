// bfs O(mn(m+n))time O(mn)space
public class Solution {
    /**
     * @param grid: a 2D grid
     * @return: An integer
     */
    public static final int WALL = 2;
    public static final int HOUSE = 1;
    public static final int EMPTY = 0;
    public static final int[] dx = new int[]{1, 0, 0, -1};
    public static final int[] dy = new int[]{0, 1, -1, 0};
    
    class Pair {
        public int x;
        public int y;
        
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 ||
            grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        int[][] counts = new int[m][n];  // the number of houses that can reach this position
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == HOUSE) {
                    count++;
                    bfs(i, j, grid, dist, counts);
                }
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == EMPTY && counts[i][j] == count) {
                    res = Math.min(res, dist[i][j]);
                }
            }
        }
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    private void bfs(int x, int y, int[][] grid, int[][] dist, int[][] counts) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(x, y));
        boolean[][] visited = new boolean[m][n];
        visited[x][y] = true;
        int level = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                Pair curr = queue.poll();
                
                for (int k = 0; k < 4; k++) {
                    int nx = curr.x + dx[k];
                    int ny = curr.y + dy[k];
                    if (valid(nx, ny, m, n, visited, grid)) {
                        dist[nx][ny] += level;
                        visited[nx][ny] = true;
                        counts[nx][ny]++;
                        queue.offer(new Pair(nx, ny));
                    }
                }
            }
                
            level++;
        }
    }
    
    private boolean valid(int x, int y, int m, int n, boolean[][] visited, int[][] grid) {
        return x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && grid[x][y] == EMPTY;
    }
}