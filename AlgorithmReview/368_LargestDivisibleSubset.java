class Solution {
    /*
     * @param nums: a set of distinct positive integers
     * @return: the largest subset 
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        int n = nums.length;
        Arrays.sort(nums);
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int[] pointers = new int[n]; // stores the last divisible element, if it points to itself, it is the end of the subsequence(subset)
        for (int i = 0; i < n; i++) {
            pointers[i] = i;
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        pointers[i] = j;
                    }
                }
            }
        }
        
        int maxIdx = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > dp[maxIdx]) {
                maxIdx = i;
            }
        }
        
        List<Integer> res = new ArrayList<>();
        int idx = maxIdx;
        res.add(nums[idx]);
        while (pointers[idx] != idx) {
            idx = pointers[idx];
            res.add(0, nums[idx]);
        }
        
        return res;
    }
}