public class Solution {
    /**
     * @param arrays: a list of array
     * @param k: An integer
     * @return: an integer, K-th largest element in N arrays
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
    
    class PairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            return o2.val - o1.val;
        }
    }
    
    public int KthInArrays(int[][] arrays, int k) {
        if (arrays == null || arrays.length == 0 || 
            arrays[0] == null) {
            return 0;
        }
        
        for (int[] array : arrays) {
            Arrays.sort(array);
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>(k, new PairComparator());
        
        for (int i = 0; i < arrays.length; i++) {
            int j = arrays[i].length - 1;
            if (j >= 0) {
                pq.offer(new Pair(i, j, arrays[i][j]));
            }
        }
        
        for (int i = 0; i < k - 1; i++) {
            Pair curr = pq.poll();
            if (curr.col - 1 >= 0) {
                pq.offer(new Pair(curr.row, curr.col - 1, arrays[curr.row][curr.col - 1]));
            }
        }
        
        return pq.peek().val;
    }
}