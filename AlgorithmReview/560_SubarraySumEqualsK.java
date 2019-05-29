class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int prefixSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int res = 0;
        
        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];
            if (map.containsKey(prefixSum - k)) {
                res += map.get(prefixSum - k);
            }
            if (!map.containsKey(prefixSum)) {
                map.put(prefixSum, 1);
            } else {
                map.put(prefixSum, map.get(prefixSum) + 1);
            }
        }
        
        return res;
    }
}