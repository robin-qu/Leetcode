public class Solution {
    /**
     * @param matrix: List[List[int]]
     * @param k: a integer
     * @return: return a integer
     */
     
    class Pair {
        public int row;
        public int col;
        public int val;
        
        public Pair(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
    
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
            
        int n = matrix.length;
        boolean[][] visited = new boolean[n][n];
        
        PriorityQueue<Pair> pq = new PriorityQueue<>(k, new Comparator<Pair>() {
            public int compare(Pair o1, Pair o2) {
                return o1.val - o2.val;
            }
        });
        
        pq.offer(new Pair(0, 0, matrix[0][0]));
        visited[0][0] = true;
        
        for (int i = 0; i < k - 1; i++) {
            Pair curr = pq.poll();
            
            if (curr.col + 1 < n && !visited[curr.row][curr.col + 1]) {
                pq.offer(new Pair(curr.row, curr.col + 1, matrix[curr.row][curr.col + 1]));
                visited[curr.row][curr.col + 1] = true;
            }
            
            if (curr.row + 1 < n && !visited[curr.row + 1][curr.col]) {
                pq.offer(new Pair(curr.row + 1, curr.col, matrix[curr.row + 1][curr.col]));
                visited[curr.row + 1][curr.col] = true;
            }
        }
        
        return pq.peek().val;
    }
}