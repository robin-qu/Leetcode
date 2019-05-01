class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int res = 0;
        int sum = 0;
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            } 
            if (!map.containsKey(sum)) {
                map.put(sum, 0);
            }
            map.put(sum, map.get(sum) + 1);
        }
        
        return res;
    }
}