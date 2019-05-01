class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m + n - 1;
        int i1 = m - 1;
        int i2 = n - 1;
        
        while (i1 >= 0 && i2 >= 0) {
            if (nums1[i1] > nums2[i2]) {
                nums1[i] = nums1[i1];
                i1--;
            } else {
                nums1[i] = nums2[i2];
                i2--;
            }
            i--;
        }
        
        while (i2 >= 0) {
            nums1[i] = nums2[i2];
            i--;
            i2--;
        }
    }
}

// class Solution {
//     public void merge(int[] nums1, int m, int[] nums2, int n) {
//         int[] res = new int[m + n];
//         int i = 0;
//         int i1 = 0;
//         int i2 = 0;
        
//         while (i1 < m && i2 < n) {
//             if (nums1[i1] <= nums2[i2]) {
//                 res[i] = nums1[i1];
//                 i1++;
//             } else {
//                 res[i] = nums2[i2];
//                 i2++;
//             }
//             i++;
//         }
        
//         while (i1 < m) {
//             res[i] = nums1[i1];
//             i1++;
//             i++;
//         }
        
//         while (i2 < n) {
//             res[i] = nums2[i2];
//             i2++;
//             i++;
//         }
        
//         for (int idx = 0; idx < m + n; idx++) {
//             nums1[idx] = res[idx];
//         }
//     }
// }