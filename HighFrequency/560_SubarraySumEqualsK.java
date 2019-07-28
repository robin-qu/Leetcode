// PrefixSum + hashmap O(n)time O(n)space
class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (prefixSum.containsKey(sum - k)) {
                count += prefixSum.get(sum - k);
            }
            
            if (!prefixSum.containsKey(sum)) {
                prefixSum.put(sum, 1);
            } else {
                prefixSum.put(sum, prefixSum.get(sum) + 1);
            }
        }
        
        return count;
    }
}