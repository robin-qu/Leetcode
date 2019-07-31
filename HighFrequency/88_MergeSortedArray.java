// Two Pointers O(m + n)time O(1)space
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null) {
            return;
        }
        
        int i1 = m - 1;
        int i2 = n - 1;
        
        for (int i = m + n - 1; i >= 0; i--) {
            int n1 = i1 >= 0 ? nums1[i1] : Integer.MIN_VALUE;
            int n2 = i2 >= 0 ? nums2[i2] : Integer.MIN_VALUE;
            
            if (n1 > n2) {
                nums1[i] = n1;
                i1--;
            } else {
                nums1[i] = n2;
                i2--;
            }
        }
    }
}