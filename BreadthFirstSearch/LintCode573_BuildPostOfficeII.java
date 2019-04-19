public class Solution {
    /**
     * @param grid: a 2D grid
     * @return: An integer
     */
     class Coor {
         public int x;
         public int y;
         
         public Coor(int x, int y) {
             this.x = x;
             this.y = y;
         }
     }
     
     
    private int[] dX = {1, 0, -1, 0};
    private int[] dY = {0, 1, 0, -1};
    private int WALL = 2;
    private int HOUSE = 1;
    private int EMPTY = 0;
     
    public int shortestDistance(int[][] grid) {
        int res = Integer.MAX_VALUE;
        int x = grid.length;
        int y = grid[0].length;
        
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return res;
        }
        
        int[][] distances = new int[x][y];
        int[][] counts = new int[x][y];
        int houseNum = 0;
        
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                distances[i][j] = Integer.MAX_VALUE;
                if (grid[i][j] == HOUSE) {
                    houseNum++;
                }
            }
        }
        
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (grid[i][j] == HOUSE) {
                    Coor source = new Coor(i, j);
                    BFS(source, grid, distances, counts);
                }
            }
        }
        
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (counts[i][j] == houseNum) {
                    res = Math.min(res, distances[i][j]);
                }
            }
        }
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    private void BFS(Coor source, int[][] grid, int[][] distances, int[][] counts) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<Coor> queue = new LinkedList<>();
        queue.add(source);
        int dist = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            dist++;
            
            while (size > 0) {
                Coor curr = queue.remove();
                visited[curr.x][curr.y] = true;
                
                for (int i = 0; i < 4; i++) {
                    Coor neighbor = new Coor(curr.x + dX[i], curr.y + dY[i]);
                    if (inBound(neighbor, grid) && !visited[neighbor.x][neighbor.y]) {
                        visited[neighbor.x][neighbor.y] = true;
                        if (distances[neighbor.x][neighbor.y] == Integer.MAX_VALUE) {
                            distances[neighbor.x][neighbor.y] = dist;
                        } else {
                            distances[neighbor.x][neighbor.y] += dist;
                        }
                        counts[neighbor.x][neighbor.y]++;
                        queue.add(neighbor);
                    }
                }
                size--;
            }
        }
    }
    
    // Returns if the point is an empty point in the grid
    private boolean inBound(Coor point, int[][] grid) {
        int x = grid.length;
        int y = grid[0].length;
        
        return point.x >= 0 && point.x < x && 
               point.y >= 0 && point.y < y &&
               grid[point.x][point.y] == EMPTY;
    }
}