// // BFS
// public class Solution {
//     /**
//      * @param image: a binary matrix with '0' and '1'
//      * @param x: the location of one of the black pixels
//      * @param y: the location of one of the black pixels
//      * @return: an integer
//      */
//     class Pair {
//         public int x;
//         public int y;
        
//         public Pair(int x, int y) {
//             this.x = x;
//             this.y = y;
//         }
//     }
    
//     public int minArea(char[][] image, int x, int y) {
//         if (image == null || image.length == 0 ||
//             image[0] == null || image[0].length == 0) {
//             return 0;
//         }
        
//         int m = image.length;
//         int n = image[0].length;
//         boolean[][] visited = new boolean[m][n];
//         int[] dx = new int[]{0, 1, -1, 0};
//         int[] dy = new int[]{1, 0, 0, -1};
//         Queue<Pair> queue = new LinkedList<>();
//         queue.offer(new Pair(x, y));
//         visited[x][y] = true;
        
//         int x1 = x;   //        y1
//         int x2 = x;   //   x1        x2
//         int y1 = y;   //        y2
//         int y2 = y;   //
        
//         while (!queue.isEmpty()) {
//             Pair curr = queue.poll();
//             x1 = Math.min(x1, curr.x);
//             x2 = Math.max(x2, curr.x);
//             y1 = Math.min(y1, curr.y);
//             y2 = Math.max(y2, curr.y);
            
//             for (int i = 0; i < 4; i++) {
//                 int nx = curr.x + dx[i];
//                 int ny = curr.y + dy[i];
//                 if (inBound(nx, ny, m, n) && !visited[nx][ny]) {
//                     visited[nx][ny] = true;
//                     if (image[nx][ny] == '1') {
//                         queue.offer(new Pair(nx, ny));
//                     }
//                 }
//             }
//         }
        
//         return (x2 - x1 + 1) * (y2 - y1 + 1);
//     }
    
//     private boolean inBound(int x, int y, int m, int n) {
//         return x >= 0 && x < m && y >= 0 && y < n;
//     }
// }


// Binary Search  O(mlogn + nlogm)
public class Solution {
    /**
     * @param image: a binary matrix with '0' and '1'
     * @param x: the location of one of the black pixels
     * @param y: the location of one of the black pixels
     * @return: an integer
     */
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 ||
            image[0] == null || image[0].length == 0) {
            return 0;
        }
        
        int m = image.length;
        int n = image[0].length;
        
        int x1 = findX1(image, x);
        int x2 = findX2(image, x, m);
        int y1 = findY1(image, y);
        int y2 = findY2(image, y, n);
        
        return (x2 - x1 + 1) * (y2 - y1 + 1);
    }
    
    private int findX1(char[][] image, int x) {
        int up = 0;
        int down = x;
        
        while (up + 1 < down) {
            int mid = up + (down - up) / 2;
            
            if (rowHasBlack(image, mid)) {
                down = mid;
            } else {
                up = mid;
            }
        }
        
        return rowHasBlack(image, up) ? up : down;
    }
    
    private int findX2(char[][] image, int x, int m) {
        int up = x;
        int down = m - 1;
        
        while (up + 1 < down) {
            int mid = up + (down - up) / 2;
            
            if (rowHasBlack(image, mid)) {
                up = mid;
            } else {
                down = mid;
            }
        }
        
        return rowHasBlack(image, down) ? down : up;
    }
    
    private int findY1(char[][] image, int y) {
        int left = 0;
        int right = y;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (colHasBlack(image, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        return colHasBlack(image, left) ? left : right;
    }
    
    private int findY2(char[][] image, int y, int n) {
        int left = y;
        int right = n - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (colHasBlack(image, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return colHasBlack(image, right) ? right : left;
    }
    
    private boolean rowHasBlack(char[][] image, int row) {
        for (int i = 0; i < image[row].length; i++) {
            if (image[row][i] == '1') {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean colHasBlack(char[][] image, int col) {
        for (int i = 0; i < image.length; i++) {
            if (image[i][col] == '1') {
                return true;
            }
        }
        
        return false;
    }
}