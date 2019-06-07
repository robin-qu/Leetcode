// // Floyd building heap algorithm : O(n)
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
    
//     private void percolateDown(int[] A, int curr, int len) {
//         int left = 2 * curr + 1;
//         int right = 2 * curr + 2;
        
//         while (left < len && right < len) {
//             if (A[left] <= A[right]) {
//                 if (A[curr] <= A[left]) {
//                     break;
//                 } else {
//                     swap(A, curr, left);
//                     curr = left;
//                 }
//             } else {
//                 if (A[curr] <= A[right]) {
//                     break;
//                 } else {
//                     swap(A, curr, right);
//                     curr = right;
//                 }
//             }
//             left = 2 * curr + 1;
//             right = 2 * curr + 2;
//         }
        
//         if (left < len && A[curr] > A[left]) {
//             swap(A, curr, left);
//         }
//     }
    
//     private void swap(int[] A, int i, int j) {
//         int temp = A[i];
//         A[i] = A[j];
//         A[j] = temp;
//     }
// }


// Floyd building heap algorithm : O(n)
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
        
        for (int i = len / 2; i >= 0; i--) {
            percolateDown(A, i, len);
        }
    }
    
    private void percolateDown(int[] A, int curr, int len) {
        while (curr < len) {
            int min = curr;
            
            if (curr * 2 + 1 < len && A[curr * 2 + 1] < A[min]) {
                min = curr * 2 + 1;
            }
            
            if (curr * 2 + 2 < len && A[curr * 2 + 2] < A[min]) {
                min = curr * 2 + 2;
            }
            
            if (curr == min) {
                break;
            }
            
            swap(A, curr, min);
            curr = min;
        }
    }
    
    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}


// // Percolateup : O(nlogn)
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
        
//         for (int i = 1; i < len; i++) {
//             percolateUp(A, i);
//         }
//     }
    
//     private void percolateUp(int[] A, int curr) {
//         while (curr != 0) {
//             int parent = (curr - 1) / 2;
//             if (A[curr] >= A[parent]) {
//                 break;
//             }
//             swap(A, curr, parent);
//             curr = parent;
//         }
//     }
    
//     private void swap(int[] A, int i, int j) {
//         int temp = A[i];
//         A[i] = A[j];
//         A[j] = temp;
//     }
// }