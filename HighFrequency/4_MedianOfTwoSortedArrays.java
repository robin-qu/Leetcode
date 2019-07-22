// O(log(m + n))time O(1)space
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return 0.0;
        }
        
        int m = nums1.length;
        int n = nums2.length;
        
        if ((m + n) % 2 == 0) {
            int median1 = find(nums1, 0, nums2, 0, (m + n) / 2);
            int median2 = find(nums1, 0, nums2, 0, (m + n) / 2 + 1);
            return (median1 + median2) / 2.0;
        }
        
        return (double) find(nums1, 0, nums2, 0, (m + n) / 2 + 1);
    }
    
    // finds the k-th smallest element in the two sorted arrays, k is not index
    private int find(int[] nums1, int i1, int[] nums2, int i2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        
        if (k == 1) {  // find the smaller element
            int head1 = (i1 < m ? nums1[i1] : Integer.MAX_VALUE);
            int head2 = (i2 < n ? nums2[i2] : Integer.MAX_VALUE);
            return Math.min(head1, head2);
        }
        
        // compare the middle of two arrays
        int mid1 = (i1 + k / 2 - 1 < m ? nums1[i1 + k / 2 - 1] : Integer.MAX_VALUE);
        int mid2 = (i2 + k / 2 - 1 < n ? nums2[i2 + k / 2 - 1] : Integer.MAX_VALUE);
        
        // eliminate the half that doesn't have the median
        if (mid1 < mid2) {
            return find(nums1, i1 + k / 2, nums2, i2, k - k / 2);
        }
        
        return find(nums1, i1, nums2, i2 + k / 2, k - k / 2);
    }
}