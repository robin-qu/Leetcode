class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Edge cases (not necessary)
        if (nums1 == null || nums1.length == 0) {
            int n = nums2.length;
            if (n % 2 == 1) {
                return nums2[n / 2];   
            }
            return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
        }
        
        if (nums2 == null || nums2.length == 0) {
            int m = nums1.length;
            if (m % 2 == 1) {
                return nums1[m / 2];
            }
            return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
        }
        
        int m = nums1.length;
        int n = nums2.length;
        
        if ((m + n) % 2 == 1) {
            return findKth(nums1, 0, nums2, 0, (m + n) / 2 + 1) * 1.0;
        }
        
        int first = findKth(nums1, 0, nums2, 0, (m + n) / 2);
        int second = findKth(nums1, 0, nums2, 0, (m + n) / 2 + 1);
        return (first + second) / 2.0;
    }
    
    private int findKth(int[] nums1, int p1, int[] nums2, int p2, int k) {
        if (k == 1) {
            int n1 = p1 >= nums1.length ? Integer.MAX_VALUE : nums1[p1];
            int n2 = p2 >= nums2.length ? Integer.MAX_VALUE : nums2[p2];
            return Math.min(n1, n2);
        }
        
        int n1 = p1 + k / 2 - 1 >= nums1.length ? Integer.MAX_VALUE : nums1[p1 + k / 2 - 1];
        int n2 = p2 + k / 2 - 1 >= nums2.length ? Integer.MAX_VALUE : nums2[p2 + k / 2 - 1];
        
        if (n1 < n2) {
            return findKth(nums1, p1 + k / 2, nums2, p2, k - k / 2);
        }
        
        return findKth(nums1, p1, nums2, p2 + k / 2, k - k / 2);
    }
}