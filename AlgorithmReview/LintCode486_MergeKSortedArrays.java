public class Solution {
    /**
     * @param arrays: k sorted integer arrays
     * @return: a sorted array
     */
     
    class Pair implements Comparable<Pair> {
        public int val;
        public int row;
        public int col;
        
        public Pair(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
        
        @Override
        public int compareTo(Pair other) {
            return this.val - other.val;
        }
    }
    
    public int[] mergekSortedArrays(int[][] arrays) {
        if (arrays == null || arrays.length == 0) {
            return new int[0];
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int size = 0;
        
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] == null || arrays[i].length == 0) {
                continue;
            }
            size += arrays[i].length;
            pq.offer(new Pair(arrays[i][0], i, 0));
        }
        
        int[] res = new int[size];
        int i = 0;
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int row = curr.row;
            int col = curr.col;
            res[i] = curr.val;
            if (col + 1 < arrays[row].length) {
                pq.add(new Pair(arrays[row][col + 1], row, col + 1));
            }
            i++;
        }
        
        return res;
    }
}

// public class Solution {
//     /**
//      * @param arrays: k sorted integer arrays
//      * @return: a sorted array
//      */
     
//     class Pair {
//         public int val;
//         public int row;
//         public int col;
        
//         public Pair(int val, int row, int col) {
//             this.val = val;
//             this.row = row;
//             this.col = col;
//         }
//     }
    
//     class PairComparator implements Comparator<Pair> {
//         @Override
//         public int compare(Pair p1, Pair p2) {
//             return p1.val - p2.val;
//         }
//     }
    
//     public int[] mergekSortedArrays(int[][] arrays) {
//         if (arrays == null || arrays.length == 0) {
//             return new int[0];
//         }
        
//         PriorityQueue<Pair> pq = new PriorityQueue<>(new PairComparator());
//         int size = 0;
        
//         for (int i = 0; i < arrays.length; i++) {
//             if (arrays[i] == null || arrays[i].length == 0) {
//                 continue;
//             }
//             size += arrays[i].length;
//             pq.offer(new Pair(arrays[i][0], i, 0));
//         }
        
//         int[] res = new int[size];
//         int i = 0;
//         while (!pq.isEmpty()) {
//             Pair curr = pq.poll();
//             int row = curr.row;
//             int col = curr.col;
//             res[i] = curr.val;
//             if (col + 1 < arrays[row].length) {
//                 pq.add(new Pair(arrays[row][col + 1], row, col + 1));
//             }
//             i++;
//         }
        
//         return res;
//     }
// }