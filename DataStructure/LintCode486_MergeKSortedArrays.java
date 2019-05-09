public class Solution {
    /**
     * @param arrays: k sorted integer arrays
     * @return: a sorted array
     */
    
    class Pair implements Comparable<Pair> {
        public int row;
        public int col;
        public int value;
        
        public Pair(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
        
        public int compareTo(Pair other) {
            return this.value - other.value;
        }
    }
    
    public int[] mergekSortedArrays(int[][] arrays) {
        if (arrays == null || arrays.length == 0) {
            return new int[]{};
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] != null && arrays[i].length != 0) {
                pq.add(new Pair(i, 0, arrays[i][0]));
            }
        }
        
        int idx = 0;
        int len = 0;
        for (int[] array : arrays) {
            len += array.length;
        }
        int[] res = new int[len];
        
        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            res[idx] = pair.value;
            if (pair.col < arrays[pair.row].length - 1) {
                pq.add(new Pair(pair.row, pair.col + 1, 
                        arrays[pair.row][pair.col + 1]));
            }
            idx++;
        }
        
        return res;
    }
}