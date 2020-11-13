// class Solution {
//     public int kthSmallest(int[][] matrix, int k) {
//         if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
//             return 0;
//         }
        
//         int m = matrix.length;
//         int n = matrix[0].length;
//         boolean[][] visited = new boolean[m][n];
//         PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
//         pq.offer(new int[] {matrix[0][0], 0, 0});
//         visited[0][0] = true;
//         for (int i = 0; i < k - 1; i++) {
//             int[] curr = pq.poll();
//             int x = curr[1];
//             int y = curr[2];
//             if (x + 1 < m && !visited[x + 1][y]) {
//                 pq.offer(new int[] {matrix[x + 1][y], x + 1, y});
//                 visited[x + 1][y] = true;
//             }
//             if (y + 1 < n && !visited[x][y + 1]) {
//                 pq.offer(new int[] {matrix[x][y + 1], x, y + 1});
//                 visited[x][y + 1] = true;
//             }
//         }
        
//         return pq.peek()[0];
//     }
// }


class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int left = matrix[0][0];
        int right = matrix[m - 1][n - 1];
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            int count = getCount(matrix, mid);
            if (count < k) {
                left = mid;
            } else if (count >= k) {
                right = mid;
            }
        }
        
        if (getCount(matrix, left) >= k) {
            return left;
        }
        
        return right;
    }
    
    private int getCount(int[][] matrix, int num) {
        int count = 0;
        int i = matrix.length - 1;
        int j = 0;
        while (i >= 0 && j < matrix[0].length) {
            if (matrix[i][j] <= num) {
                j++;
                count += i + 1;
            } else {
                i--;
            }
        }
        
        return count;
    }
}