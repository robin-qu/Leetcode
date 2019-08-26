// HashMap O(m + n)time O(m)space
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 ||
            nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums1) {
           count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        List<Integer> resList = new ArrayList<>();
        for (int num : nums2) {
            if (count.containsKey(num) && count.get(num) > 0) {
                resList.add(num);
                count.put(num, count.get(num) - 1);
            }
        }
        
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        
        return res;
    }
}