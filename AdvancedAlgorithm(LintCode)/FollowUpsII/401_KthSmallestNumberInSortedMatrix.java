public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @param k: An integer
     * @return: the kth smallest number in the matrix
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
    
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(0, 0, matrix[0][0]));
        visited[0][0] = true;
        
        for (int i = 0; i < k - 1; i++) {
            Pair curr = pq.poll();
            int x = curr.x;
            int y = curr.y;
            int nx = x + 1;
            int ny = y + 1;
            
            if (nx < m && !visited[nx][y]) {
                pq.offer(new Pair(nx, y, matrix[nx][y]));
                visited[nx][y] = true;
            }
            
            if (ny < n && !visited[x][ny]) {
                pq.offer(new Pair(x, ny, matrix[x][ny]));
                visited[x][ny] = true;
            }
        }
        
        return pq.peek().val;
    }
}
