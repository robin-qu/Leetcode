public class Solution {
    /*
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return new int[]{0, 0};
        }
        
        int n = nums.length;
        
        int[] prefixSum = new int[n + 1];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
            if (map.containsKey(prefixSum[i + 1])) {
                return new int[]{map.get(prefixSum[i + 1]) + 1, i};
            }
            map.put(prefixSum[i + 1], i);
        }
        
        Arrays.sort(prefixSum);
        
        int closest = Integer.MAX_VALUE;
        int[] res = new int[2];
        for (int i = 0; i < n; i++) {
            if (prefixSum[i + 1] - prefixSum[i] < closest) {
                closest = prefixSum[i + 1] - prefixSum[i];
                res[0] = Math.min(map.get(prefixSum[i + 1]), map.get(prefixSum[i])) + 1;
                res[1] = Math.max(map.get(prefixSum[i + 1]), map.get(prefixSum[i]));
            }
        }
        
        return res;
    }
}