public class Solution {
    /**
     * @param grid: a 2D grid
     * @return: An integer
     */
    public static final int WALL = 2;
    public static final int HOUSE = 1;
    public static final int EMPTY = 0;
    public static final int[] DX = {0, 1, -1, 0};
    public static final int[] DY = {1, 0, 0, -1};
    
    class Coor {
        public int x;
        public int y;
        
        public Coor(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int[][] distances = new int[m][n];
        int[][] counts = new int[m][n];
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == HOUSE) {
                    count++;
                    bfs(new Coor(i, j), grid, distances, counts);
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == EMPTY && counts[i][j] == count) {
                    min = Math.min(min, distances[i][j]);
                }
            }
        }
        
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    private void bfs(Coor point, int[][] grid, 
                     int[][]distances, int[][] counts) {
        Queue<Coor> queue = new LinkedList<>();
        queue.offer(point);
        int dist = 1;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[point.x][point.y] = true;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                Coor curr = queue.poll();
                
                for (int j = 0; j < 4; j++) {
                    Coor neighbor = new Coor(curr.x + DX[j], curr.y + DY[j]);
                    if (valid(neighbor.x, neighbor.y, grid) && !visited[neighbor.x][neighbor.y]) {
                        queue.offer(neighbor);
                        visited[neighbor.x][neighbor.y] = true;
                        distances[neighbor.x][neighbor.y] += dist;
                        counts[neighbor.x][neighbor.y]++;
                    }
                }
            }
            dist++;
        }
        
    }
    
    private boolean valid(int i, int j, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == EMPTY;
    }
}