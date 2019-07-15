public class Solution {
    /*
     * @param matrix: an integer matrix
     * @return: the coordinate of the left-up and right-down number
     */
    public int[][] submatrixSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return new int[2][2];
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[][] sum = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = matrix[i - 1][j - 1] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }
        
        int[][] res = new int[2][2];
        for (int up = 0; up < m; up++) {  // upper boundary
            for (int down = up; down < m; down++) {  // lower boundary
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 0);
                for (int k = 1; k <= n; k++) {
                    int diff = sum[down + 1][k] - sum[up][k];
                    if (map.containsKey(diff)) {
                        res[0] = new int[]{up, map.get(diff)};
                        res[1] = new int[]{down, k - 1};
                        return res;
                    } else {
                        map.put(diff, k);
                    }
                }
            }
        }
        
        return res;
    }
}