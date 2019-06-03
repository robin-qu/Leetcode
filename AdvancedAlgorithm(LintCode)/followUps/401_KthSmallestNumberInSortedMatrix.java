// public class Solution {
//     /**
//      * @param matrix: a matrix of integers
//      * @param k: An integer
//      * @return: the kth smallest number in the matrix
//      */
//     class Pair {
//         public int row;
//         public int col;
//         public int val;
        
//         public Pair(int row, int col, int val) {
//             this.row = row;
//             this.col = col;
//             this.val = val;
//         }
//     }
    
//     public int kthSmallest(int[][] matrix, int k) {
//         if (matrix == null || matrix.length == 0 ||
//             matrix[0] == null || matrix[0].length == 0) {
//             return 0;
//         }
            
//         int m = matrix.length;
//         int n = matrix[0].length;
//         boolean[][] visited = new boolean[m][n];
        
//         PriorityQueue<Pair> pq = new PriorityQueue<>(k, new Comparator<Pair>() {
//             public int compare(Pair o1, Pair o2) {
//                 return o1.val - o2.val;
//             }
//         });
        
//         pq.offer(new Pair(0, 0, matrix[0][0]));
//         visited[0][0] = true;
//         int[] dX = new int[]{0, 1};
//         int[] dY = new int[]{1, 0};
        
//         for (int i = 0; i < k - 1; i++) {
//             Pair curr = pq.poll();
            
//             for (int j = 0; j < 2; j++) {
//                 int row = curr.row + dY[j];
//                 int col = curr.col + dX[j];
//                 if (validPos(row, col, visited)) {
//                     pq.offer(new Pair(row, col, matrix[row][col]));
//                     visited[row][col] = true;
//                 }
//             }
//         }
        
//         return pq.peek().val;
//     }
    
//     private boolean validPos(int x, int y, boolean[][] visited) {
//         return x < visited.length && y < visited[0].length && !visited[x][y];
//     }
// }


// binary search
public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @param k: An integer
     * @return: the kth smallest number in the matrix
     */
     
    class ResultType {
        public boolean exist;
        public int pos;
        
        public ResultType(boolean exist, int pos) {
            this.exist = exist;
            this.pos = pos;
        }
    }
     
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
            
        int m = matrix.length;
        int n = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[m - 1][n - 1];
        int mid;
        
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            ResultType res = getPos(mid, matrix);
            
            if (res.exist && res.pos == k) {
                return mid;
            } else if (res.pos < k) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        if (getPos(left, matrix).pos == k) {
            return left;
        }
        
        return right;
    }
    
    public ResultType getPos(int value, int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int row = 0;
        int col = n - 1;
        
        boolean exist = false;
        int count = 0;
        while (row < m && col >= 0) {
            if (matrix[row][col] <= value) {
                if (matrix[row][col] == value) {
                    exist = true;
                }
                count += col + 1;
                row++;
            } else {
                col--;
            }
        }
        
        return new ResultType(exist, count);
    }
}