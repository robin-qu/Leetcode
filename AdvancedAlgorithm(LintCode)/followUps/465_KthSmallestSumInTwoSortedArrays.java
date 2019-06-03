public class Solution {
    /**
     * @param A: an integer arrays sorted in ascending order
     * @param B: an integer arrays sorted in ascending order
     * @param k: An integer
     * @return: An integer
     */
     
    class Pair {
        public int a;
        public int b;
        public int val;
        
        public Pair(int a, int b, int val) {
            this.a = a;
            this.b = b;
            this.val = val;
        }
    }
    
    class PairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.val - o2.val;
        }
    }
     
    public int kthSmallestSum(int[] A, int[] B, int k) {
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return 0;
        }
        
        int m = A.length;
        int n = B.length;
        int[] dA = new int[]{0, 1};
        int[] dB = new int[]{1, 0};
        
        PriorityQueue<Pair> pq = new PriorityQueue<>(k, new PairComparator());
        
        pq.offer(new Pair(0, 0, A[0] + B[0]));
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        
        for (int i = 0; i < k - 1; i++) {
            Pair curr = pq.poll();
            
            for (int j = 0; j < 2; j++) {
                int nextA = curr.a + dA[j];
                int nextB = curr.b + dB[j];
                
                if (nextA < m && nextB < n && !visited[nextA][nextB]) {
                    pq.offer(new Pair(nextA, nextB, A[nextA] + B[nextB]));
                    visited[nextA][nextB] = true;
                }
            }
        }
        
        return pq.peek().val;
    }
}