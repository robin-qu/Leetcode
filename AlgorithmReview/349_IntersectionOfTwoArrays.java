// class Solution {
//     public int[] intersection(int[] nums1, int[] nums2) {
//         if (nums1 == null || nums1.length == 0 || 
//             nums2 == null || nums2.length == 0) {
//             return new int[]{};
//         }
        
//         Set<Integer> nums1Set = new HashSet<>();
        
//         for (int n : nums1) {
//             nums1Set.add(n);
//         }
        
//         Set<Integer> intersection = new HashSet<>();
        
//         for (int n : nums2) {
//             if (nums1Set.contains(n)) {
//                 intersection.add(n);
//             }
//         }
        
//         int[] res = new int[intersection.size()];
        
//         int idx = 0;
//         for (int n : intersection) {
//             res[idx++] = n;
//         }
        
//         return res;
//     }
// }


class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || 
            nums2 == null || nums2.length == 0) {
            return new int[]{};
        }
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0;
        int p2 = 0;
        Set<Integer> intersection = new HashSet<>();
        
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                intersection.add(nums1[p1]);
                p1++;
                p2++;
            }
        }
        
        int[] res = new int[intersection.size()];
        int idx = 0;
        
        for (int n : intersection) {
            res[idx] = n;
            idx++;
        }
        
        return res;
    }
}