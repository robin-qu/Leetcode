// class Solution {
//     private static final int MARKER = 8573409;
//     public void setZeroes(int[][] matrix) {
//         if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
//             return;
//         }

//         int m = matrix.length;
//         int n = matrix[0].length;

//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (matrix[i][j] == 0) {
//                     for (int k = 0; k < m; k++) {
//                         if (matrix[k][j] != 0) {
//                             matrix[k][j] = MARKER;
//                         }
//                     }
//                     for (int k = 0; k < n; k++) {
//                         if (matrix[i][k] != 0) {
//                             matrix[i][k] = MARKER;
//                         }
//                     }
//                 }
//             }
//         }

//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (matrix[i][j] == MARKER) {
//                     matrix[i][j] = 0;
//                 }
//             }
//         }
//     }
// }



class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        boolean setFirstCol = false;
        boolean setFirstRow = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                setFirstCol = true;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                setFirstRow = true;
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        if (setFirstCol) {
            for (int j = 0; j < m; j++) {
                matrix[j][0] = 0;
            }
        }

        if (setFirstRow) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }
}