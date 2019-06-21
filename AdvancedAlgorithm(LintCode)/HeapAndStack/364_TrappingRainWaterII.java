public class Solution {
    /**
     * @param heights: a matrix of integers
     * @return: an integer
     */
    class Pair implements Comparable<Pair> {
        public int x;
        public int y;
        public int val;
        
        public Pair(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
        
        @Override
        public int compareTo(Pair other) {
            return this.val - other.val;
        }
    }
    
    public int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0 ||
            heights[0] == null || heights[0].length == 0) {
            return 0;
        }
        
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] dx = new int[]{0, 1, -1, 0};
        int[] dy = new int[]{1, 0, 0, -1};
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int res = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    pq.offer(new Pair(i, j, heights[i][j]));
                    visited[i][j] = true;
                }
            }
        }
        
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int x = curr.x;
            int y = curr.y;
            int val = curr.val;
            
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                
                if (!inBound(nx, ny, m, n) || visited[nx][ny]) {
                    continue;
                }
                
                int waterHeight = Math.max(val, heights[nx][ny]);
                res += waterHeight - heights[nx][ny];
                
                pq.offer(new Pair(nx, ny, waterHeight));
                visited[nx][ny] = true;
            }
        }
        
        return res;
    }
    
    private boolean inBound(int x, int y, int m, int n) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }
}