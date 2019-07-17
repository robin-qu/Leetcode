/**
 * public class NBCompare {
 *     public int cmp(String a, String b);
 * }
 * You can use compare.cmp(a, b) to compare nuts "a" and bolts "b",
 * if "a" is bigger than "b", it will return 1, else if they are equal,
 * it will return 0, else if "a" is smaller than "b", it will return -1.
 * When "a" is not a nut or "b" is not a bolt, it will return 2, which is not valid.
*/
//O(n^2)
public class Solution {
    /**
     * @param nuts: an array of integers
     * @param bolts: an array of integers
     * @param compare: a instance of Comparator
     * @return: nothing
     */
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        if (nuts == null || bolts == null || nuts.length != bolts.length) {
            return;
        }
        
        int n = nuts.length;
        
        for (int i = 0; i < n; i++) {
            int idx = partition(nuts[i], bolts, i, n - 1, compare);
            swap(i, idx, bolts);
        }
    }
    
    private int partition(String pivot, String[] A, int start, int end, NBComparator compare) {
        if (start == end) {
            return start;
        }
        
        int left = start;
        int right = end;
        
        for (int i = start; i <= end; i++) {
            if (compare.cmp(A[i], pivot) == 0 || compare.cmp(pivot, A[i]) == 0) {
                swap(left, i, A);
                left++;
                break;
            }
        }
        
        while (left <= right) {
            while (left <= right && (compare.cmp(pivot, A[left]) == 1 || compare.cmp(A[left], pivot) == -1)) {
                left++;
            }
            
            while (left <= right && (compare.cmp(pivot, A[right]) == -1) || (compare.cmp(A[right], pivot) == 1)) {
                right--;
            }
            
            if (left <= right) {
                swap(left, right, A);
                left++;
                right--;
            }
        }
        
        swap(right, start, A);
        
        return right;
    }
    
    private void swap(int i, int j, String[] A) {
        String temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
};



// /**
//  * public class NBCompare {
//  *     public int cmp(String a, String b);
//  * }
//  * You can use compare.cmp(a, b) to compare nuts "a" and bolts "b",
//  * if "a" is bigger than "b", it will return 1, else if they are equal,
//  * it will return 0, else if "a" is smaller than "b", it will return -1.
//  * When "a" is not a nut or "b" is not a bolt, it will return 2, which is not valid.
// */
// // O(nlog(n))
// public class Solution {
//     /**
//      * @param nuts: an array of integers
//      * @param bolts: an array of integers
//      * @param compare: a instance of Comparator
//      * @return: nothing
//      */
//     public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
//         if (nuts == null || bolts == null || nuts.length != bolts.length) {
//             return;
//         }
        
//         int n = nuts.length;
        
//         quickSort(nuts, bolts, 0, n - 1, compare);
//     }
    
//     private void quickSort(String[] nuts, String[] bolts, int start, int end, NBComparator compare) {
//         if (start >= end) {
//             return;
//         }
        
//         int idx = partition(nuts[start + (end - start) / 2], bolts, start, end, compare);
//         partition(bolts[idx], nuts, start, end, compare);
        
//         quickSort(nuts, bolts, start, idx - 1, compare);
//         quickSort(nuts, bolts, idx + 1, end, compare);
//     }
    
//     private int partition(String pivot, String[] A, int start, int end, NBComparator compare) {
//         if (start == end) {
//             return start;
//         }
        
//         int left = start;
//         int right = end;
        
//         for (int i = start; i <= end; i++) {
//             if (compare.cmp(A[i], pivot) == 0 || compare.cmp(pivot, A[i]) == 0) {
//                 swap(left, i, A);
//                 left++;
//                 break;
//             }
//         }
        
//         while (left <= right) {
//             while (left <= right && (compare.cmp(pivot, A[left]) == 1 || compare.cmp(A[left], pivot) == -1)) {
//                 left++;
//             }
            
//             while (left <= right && (compare.cmp(pivot, A[right]) == -1) || (compare.cmp(A[right], pivot) == 1)) {
//                 right--;
//             }
            
//             if (left <= right) {
//                 swap(left, right, A);
//                 left++;
//                 right--;
//             }
//         }
        
//         swap(right, start, A);
        
//         return right;
//     }
    
//     private void swap(int i, int j, String[] A) {
//         String temp = A[i];
//         A[i] = A[j];
//         A[j] = temp;
//     }
// };