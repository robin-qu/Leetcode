public class Solution {
    /*
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        if (nums.length == 1) {
            return new int[]{0, 0};
        }
        
        int n = nums.length;
        int[] res = new int[2];
        int[] prefixSums = new int[n + 1];
        Map<Integer, Integer> map = new HashMap<>();
        prefixSums[0] = 0;
        map.put(0, -1);
        
        
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
            if (map.containsKey(prefixSums[i + 1])) {
                res[0] = map.get(prefixSums[i + 1]) + 1;
                res[1] = i;
                return res;
            }
            map.put(prefixSums[i + 1], i);
        }
        
        Arrays.sort(prefixSums);
        
        int minGap = Integer.MAX_VALUE;
        int prefixSum1 = 0;
        int prefixSum2 = 0;
        
        for (int i = 0; i < n; i++) {
            if (prefixSums[i + 1] - prefixSums[i] < minGap) {
                minGap = prefixSums[i + 1] - prefixSums[i];
                prefixSum1 = prefixSums[i];
                prefixSum2 = prefixSums[i + 1];
            }
        }
        
        if (map.get(prefixSum1) < map.get(prefixSum2)) {
            res[0] = map.get(prefixSum1) + 1;
            res[1] = map.get(prefixSum2);
        } else {
            res[0] = map.get(prefixSum2) + 1;
            res[1] = map.get(prefixSum1);
        }
        
        return res;
    }
} 