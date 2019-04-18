class Solution {
    class Coor {
        public int x;
        public int y;
        
        public Coor(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int numIslands(char[][] grid) {
        int count = 0;
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return count;
        }
        
        int x = grid.length;
        int y = grid[0].length;
        boolean[][] visited = new boolean[x][y];
        
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) { 
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    if (grid[i][j] == '1') {
                        count++;
                        Coor curr = new Coor(i, j);
                        BFS(curr, grid, visited);
                    }
                }
            }
        }
        
        return count;
    }
    
    private void BFS(Coor point, char[][] grid, boolean[][] visited) {
        Queue<Coor> queue = new LinkedList<>();
        queue.add(point);
        
        int[] dX = {0, 1, -1, 0};
        int[] dY = {1, 0, 0, -1};
        
        while (!queue.isEmpty()) {
            Coor curr = queue.remove();
            
            for (int i = 0; i < 4; i++) {
                Coor neighbor = new Coor(curr.x + dX[i], curr.y + dY[i]);
                if (inBound(neighbor, grid) && !visited[neighbor.x][neighbor.y]) {
                    visited[neighbor.x][neighbor.y] = true;
                    if (grid[neighbor.x][neighbor.y] == '1') {
                        queue.add(neighbor);
                    }
                }
            }
        }
    }
    
    private boolean inBound(Coor point, char[][] grid) {
        int x = grid.length;
        int y = grid[0].length;
        return point.x < x && point.x >= 0 && point.y >= 0 && point.y < y;
    }
}