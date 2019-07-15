public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> subarraySum(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(sum, 0);
        
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            
            if (map.containsKey(sum)) {
                res.add(map.get(sum));
                res.add(i);
                return res;
            } else {
                map.put(sum, i + 1);
            }
        }
        
        return res;
    }
}