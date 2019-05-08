
// // O(n) Floyd's building heap algorithm
// public class Solution {
//     /*
//      * @param A: Given an integer array
//      * @return: nothing
//      */
//     public void heapify(int[] A) {
//         if (A == null || A.length == 0) {
//             return;
//         }
        
//         int len = A.length;
//         for (int i = len - 1; i >= 0; i--) {
//             int idx = i;
//             int left = 2 * idx + 1;
//             int right = 2 * idx + 2;
//             while (idx < len && right < len && A[idx] > Math.min(A[left], A[right])) {
//                 if (A[left] < A[right]) {
//                     swap(A, idx, left);
//                     idx = left;
//                     left = 2 * idx + 1;
//                     right = 2 * idx + 2;
//                 } else {
//                     swap(A, idx, right);
//                     idx = right;
//                     left = 2 * idx + 1;
//                     right = 2 * idx + 2;
//                 }
//             }
//             if (left < len && A[idx] > A[left]) {
//                 swap(A, idx, left);
//             }
//         }
//     }
    
//     private void swap(int[] A, int i, int j) {
//         int temp = A[i];
//         A[i] = A[j];
//         A[j] = temp;
//     }
// }



// // O(n) Floyd's building heap algorithm
// public class Solution {
//     /*
//      * @param A: Given an integer array
//      * @return: nothing
//      */
//     public void heapify(int[] A) {
//         if (A == null || A.length == 0) {
//             return;
//         }
        
//         int len = A.length;
//         for (int i = len / 2; i >= 0; i--) {
//             percolateDown(A, i, len);
//         }
//     }
    
//     private void percolateDown(int[] A, int i, int len) {
//         while (i < len) {
//             int minIdx = i;
//             if (2 * i + 1 < len && A[2 * i + 1] < A[minIdx]) {
//                 minIdx = 2 * i + 1;
//             }
//             if (2 * i + 2 < len && A[2 * i + 2] < A[minIdx]) {
//                 minIdx = 2 * i + 2;
//             }
//             if (minIdx == i) {
//                 break;
//             }
            
//             swap(A, i, minIdx);
//             i = minIdx;
//         }
//     }
    
//     private void swap(int[] A, int i, int j) {
//         int temp = A[i];
//         A[i] = A[j];
//         A[j] = temp;
//     }
// }

// O(nlogn) ordinary building heap algorithm
public class Solution {
    /*
     * @param A: Given an integer array
     * @return: nothing
     */
    public void heapify(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }
        
        int len = A.length;
        for (int i = 1; i < len; i++) {
            percolateUp(A, i);
        }
    }
    
    private void percolateUp(int[] A, int i) {
        while (i > 0) {
            if (A[i] <= A[(i - 1) / 2]) {
                swap(A, i, (i - 1) / 2);
                i = (i - 1) / 2;
            } else {
                break;
            }
        }
    }
    
    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}