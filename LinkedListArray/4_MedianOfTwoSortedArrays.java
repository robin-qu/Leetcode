class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null) {
            return nums2[(nums2.length - 1) / 2];
        }
        
        if (nums2 == null) {
            return nums1[(nums2.length - 1) / 2];
        }
        
        int m = nums1.length;
        int n = nums2.length;
        
        if ((m + n) % 2 == 0) {
            int first = findKth(nums1, 0, nums2, 0, (m + n) / 2);
            int second = findKth(nums1, 0, nums2, 0, (m + n) / 2 + 1);
            return (first + second) / 2.0;
        }
        
        return findKth(nums1, 0, nums2, 0, (m + n) / 2 + 1) / 1.0;
    }
    
    private int findKth(int[] nums1, int i1, int[] nums2, int i2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        
        if (i1 >= m) {
            return nums2[i2 + k - 1];
        }
        
        if (i2 >= n) {
            return nums1[i1 + k - 1];
        }
        
        if (k == 1) {
            return Math.min(nums1[i1], nums2[i2]);
        }
        
        int val1 = i1 + k / 2 - 1 >= m ? 
            Integer.MAX_VALUE : nums1[i1 + k / 2 - 1];
        int val2 = i2 + k / 2 - 1 >= n ? 
            Integer.MAX_VALUE : nums2[i2 + k / 2 - 1];
        
        if (val1 < val2) {
            return findKth(nums1, i1 + k / 2, nums2, i2, k - k / 2);
        }
        
        return findKth(nums1, i1, nums2, i2 + k / 2, k - k / 2);
    }
}