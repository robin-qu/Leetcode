class Solution {
    class Pair implements Comparable<Pair> {
        public int row;
        public int col;
        public int value;
        
        public Pair(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
        
        @Override
        public int compareTo(Pair other) {
            return this.value - other.value;
        }
    }
    
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }
        
        int n = matrix.length;
        PriorityQueue<Pair> pq = new PriorityQueue<>(k);
        boolean[][] visited = new boolean[n][n];
        pq.add(new Pair(0, 0, matrix[0][0]));
        visited[0][0] = true;
        
        while (!pq.isEmpty() && k > 1) {
            Pair curr = pq.poll();
            int row = curr.row;
            int col = curr.col;
            
            if (col + 1 < n && !visited[row][col + 1]) {
                pq.add(new Pair(row, col + 1, matrix[row][col + 1]));
                visited[row][col + 1] = true;
            }
            if (row + 1 < n && !visited[row + 1][col]) {
                pq.add(new Pair(row + 1, col, matrix[row + 1][col]));
                visited[row + 1][col] = true;
            }
            
            k--;
        }
        
        return pq.peek().value;
    }
}