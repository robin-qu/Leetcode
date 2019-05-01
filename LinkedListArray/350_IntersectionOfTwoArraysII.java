class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 ||
            nums2 == null || nums2.length == 0) {
            return new int[] {};
        }
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length;
        int len2 = nums2.length;
        int i1 = 0;
        int i2 = 0;
        List<Integer> res = new ArrayList<>();
        
        while (i1 < len1 && i2 < len2) {
            if (nums1[i1] < nums2[i2]) {
                i1++;
            } else if (nums1[i1] > nums2[i2]) {
                i2++;
            } else {
                res.add(nums1[i1]);
                i1++;
                i2++;
            }
        }
        
        int i = 0;
        int[] intersection = new int[res.size()];
        for (int num : res) {
            intersection[i] = num;
            i++;
        }
        
        return intersection;
    }
}