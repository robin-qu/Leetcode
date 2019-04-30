// // Two sets
// class Solution {
//     public int[] intersection(int[] nums1, int[] nums2) {
//         Set<Integer> set = new HashSet<>();
//         Set<Integer> res = new HashSet<>();
//         int count = 0;
        
//         // Put the shorter array in hashset
//         if (nums1.length < nums2.length) {
//             for (int num : nums1) {
//                 set.add(num);
//             }
            
//             for (int num : nums2) {
//                 if (set.contains(num)) {
//                     res.add(num);
//                 }
//             }
//         } else {
//             for (int num : nums2) {
//                 set.add(num);
//             }
            
//             for (int num : nums1) {
//                 if (set.contains(num)) {
//                     res.add(num);
//                 }
//             }
//         }
        
//         int[] intersection = new int[res.size()];
//         int idx = 0;
//         for (int num : res) {
//             intersection[idx] = num;
//             idx++;
//         }
        
//         return intersection;
//     }
// }

// Sort two arrays
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
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
        Set<Integer> res = new HashSet<>();
        
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

// Sort one array and binary search