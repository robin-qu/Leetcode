class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return 0.0;
        }
        
        int m = nums1.length;
        int n = nums2.length;
        
        if ((m + n) % 2 == 1) {
            return (double) helper(nums1, 0, nums2, 0, (m + n) / 2 + 1);
        }
        
        int res1 = helper(nums1, 0, nums2, 0, (m + n) / 2);
        int res2 = helper(nums1, 0, nums2, 0, (m + n) / 2 + 1);
        
        return ((double) (res1 + res2)) / 2.0;
    }
            
    // k is count
    private int helper(int[] nums1, int s1, int[] nums2, int s2, int k) {
        if (k == 1) {
            int val1 = s1 < nums1.length ? nums1[s1] : Integer.MAX_VALUE;
            int val2 = s2 < nums2.length ? nums2[s2] : Integer.MAX_VALUE;
            return Math.min(val1, val2);
        }
        
        int idx1 = s1 + k / 2 - 1;
        int idx2 = s2 + k / 2 - 1;
        int mid1 = idx1 < nums1.length ? nums1[idx1] : Integer.MAX_VALUE;
        int mid2 = idx2 < nums2.length ? nums2[idx2] : Integer.MAX_VALUE;
        
        if (mid1 <= mid2) {
            return helper(nums1, idx1 + 1, nums2, s2, k - k / 2);
        }
        
        return helper(nums1, s1, nums2, idx2 + 1, k - k / 2);
    }
}