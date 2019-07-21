// // O(mlogn)
// public class Solution {
//     /*
//      * @param A: An integer matrix
//      * @return: The index of the peak
//      */
//     public List<Integer> findPeakII(int[][] A) {
//         if (A == null || A.length == 0 ||
//             A[0] == null || A[0].length == 0) {
//             return new ArrayList<>();
//         }
        
//         int n = A.length;
//         int m = A[0].length;
        
//         int up = 1;
//         int down = n - 2;
//         int mid;
//         List<Integer> res = new ArrayList<>();
        
//         while (up + 1 < down) {
//             mid = up + (down - up) / 2;
            
//             int col = findSinglePeak(A, mid);
            
//             if (A[mid][col] < A[mid + 1][col]) {
//                 up = mid;
//             } else if (A[mid][col] < A[mid - 1][col]) {
//                 down = mid;
//             } else {
//                 res.add(mid);
//                 res.add(col);
//                 return res;
//             }
//         }
        
//         int col = findSinglePeak(A, up + (down - up) / 2);
//         if (A[up][col] > A[down][col]) {
//             res.add(up);
//         } else {
//             res.add(down);
//         }
//         res.add(col);
        
//         return res;
//     }

//     // find max of a row
//     private int findSinglePeak(int[][] A, int row) {
//         int col = 0;
//         for (int i = 0; i < A[row].length; i++) {
//             if (A[row][i] > A[row][col]) {
//                 col = i;
//             }
//         }
//         return col;
//     }
// }


// // O(m + n)
// public class Solution {
//     /*
//      * @param A: An integer matrix
//      * @return: The index of the peak
//      */
//     public List<Integer> findPeakII(int[][] A) {
//         if (A == null || A.length == 0 ||
//             A[0] == null || A[0].length == 0) {
//             return new ArrayList<>();
//         }
        
//         int n = A.length;
//         int m = A[0].length;
        
//         return search(1, n - 2, 1, m - 2, A);
//     }
    
//     private List<Integer> search(int x1, int x2, int y1, int y2, int[][] A) {
//         int midx = x1 + (x2 - x1) / 2;
//         int midy = y1 + (y2 - y1) / 2;
        
//         // find maximum
//         int max = A[midx][midy];
//         int x = midx;
//         int y = midy;
        
//         for (int i = x1; i <= x2; i++) {
//             if (A[i][midy] > max) {
//                 max = A[i][midy];
//                 x = i;
//                 y = midy;
//             }
//         }
        
//         for (int i = y1; i <= y2; i++) {
//             if (A[midx][i] > max) {
//                 max = A[midx][i];
//                 x = midx;
//                 y = i;
//             }
//         }
        
//         boolean isPeak = true;
        
//         if (A[x - 1][y] > A[x][y]) {
//             isPeak = false;
//             x--;
//         } else if (A[x + 1][y] > A[x][y]) {
//             isPeak = false;
//             x++;
//         } else if (A[x][y - 1] > A[x][y]) {
//             isPeak = false;
//             y--;
//         } else if (A[x][y + 1] > A[x][y]) {
//             isPeak = false;
//             y++;
//         }
        
//         if (isPeak) {
//             List<Integer> res = new ArrayList<>();
//             res.add(x);
//             res.add(y);
//             return res;
//         }
        
//         if (x >= x1 && x < midx && y >= y1 && y < midy) {
//             return search(x1, midx - 1, y1, midy - 1, A);
//         }
        
//         if (x >= x1 && x < midx && y >= midy && y <= y2) {
//             return search(x1, midx - 1, midy + 1, y2, A);
//         }
        
//         if (x >= midx && x <= x2 && y >= y1 && y < midy) {
//             return search(midx + 1, x2, y1, midy - 1, A);
//         }
        
//         if (x >= midx && x <= x2 && y >= midy && y <= y2) {
//             return search(midx + 1, x2, midy + 1, y2, A);
//         }
        
//         return new ArrayList<Integer>(Arrays.asList(-1, -1));
        
//     }
// }


// // Greedy
// public class Solution {
//     /*
//      * @param A: An integer matrix
//      * @return: The index of the peak
//      */
//     public List<Integer> findPeakII(int[][] A) {
//         List<Integer> ans = Arrays.asList(new Integer[]{0, 0});
//         helper(A, 0, 0, ans);
//         return ans;
//     }
    
//     public void helper(int[][] A, int x, int y, List<Integer> ans) {
//         if (A[x][y] > A[ans.get(0)][ans.get(1)]) {
//             ans.set(0, x);
//             ans.set(1, y);
//         }
//         int newx = A[x + 1][y] > A[x][y + 1] ? x + 1 : x;
//         int newy = A[x + 1][y] > A[x][y + 1] ? y : y + 1;
//         if (A[newx][newy] >= A[x][y]) {
//             helper(A, newx, newy, ans);
//         }
//     }
// }



// Binary Search
public class Solution {
    /*
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
        if (A == null || A.length < 3 ||
            A[0] == null || A[0].length < 3) {
            return new ArrayList<>();
        }
        
        int m = A.length;
        int n = A[0].length;
        
        return search(1, m - 2, 1, n - 2, A);
    }
    
    private List<Integer> search(int x1, int x2, int y1, int y2, int[][] A) {
        int midX = x1 + (x2 - x1) / 2;  // central row 
        int midY = y1 + (y2 - y1) / 2;  // central column
        
        int maxX = midX;
        int maxY = midY;  // (maxX, maxY) is the position of the max element in the central cross
        int max = A[maxX][maxY];
        
        // find the max element in the central cross
        for (int i = x1; i <= x2; i++) {
            if (A[i][midY] > max) {
                max = A[i][midY];
                maxX = i;
                maxY = midY;
            }
        }
        
        for (int i = y1; i <= y2; i++) {
            if (A[midX][i] > max) {
                max = A[midX][i];
                maxX = midX;
                maxY = i;
            }
        }
        
        boolean isPeak = true;
        
        // check the neighbors of the max element
        if (A[maxX - 1][maxY] > A[maxX][maxY]) {
            isPeak = false;
            maxX--;
        } else if (A[maxX + 1][maxY] > A[maxX][maxY]) {
            isPeak = false;
            maxX++;
        } else if (A[maxX][maxY - 1] > A[maxX][maxY]) {
            isPeak = false;
            maxY--;
        } else if (A[maxX][maxY + 1] > A[maxX][maxY]) {
            isPeak = false;
            maxY++;
        } else {
            List<Integer> res = new ArrayList<>();
            res.add(maxX);
            res.add(maxY);
            return res;
        }
        
        if (maxX >= x1 && maxX <= midX && maxY >= y1 && maxY <= midY) {
            return search(x1, midX - 1, y1, midY - 1, A);
        }
        
        if (maxX >= midX && maxX <= x2 && maxY >= y1 && maxY <= midY) {
            return search(midX + 1, x2, y1, midY - 1, A);
        }
        
        if (maxX >= x1 && maxX <= midX && maxY >= midY && maxY <= y2) {
            return search(x1, midX - 1, midY + 1, y2, A);
        }
        
        if (maxX >= midX && maxX <= x2 && maxY >= midY && maxY <= y2) {
            return search(midX + 1, x2, midY + 1, y2, A);
        }
        
        List<Integer> res = new ArrayList<>();
        res.add(-1);
        res.add(-1);
        return res;
    }
}


