// O(n1 + n2)time O(n1)space
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 ||
            nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        
        Set<Integer> set = new HashSet<>();
        
        for (int num1 : nums1) {
            set.add(num1);
        }
        
        List<Integer> resList = new ArrayList<>();
        for (int num2 : nums2) {
            if (set.contains(num2)) {
                resList.add(num2);
                set.remove(num2);
            }
        }
        
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        
        return res;
    }
}