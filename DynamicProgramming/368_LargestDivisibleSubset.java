class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        Arrays.sort(nums);
        int len = nums.length;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int[] prev = new int[len];
        prev[0] = 0;
        
        for (int i = 0; i < len; i++) {
            prev[i] = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
        }
        
        int maxIdx = 0;
        int max = 0;
        for (int i = 0; i < len; i++) {
            if (dp[i] > max) {
                max = dp[i];
                maxIdx = i;
            }
        }
        
        List<Integer> res = new ArrayList<>();
        res.add(nums[maxIdx]);
        while (prev[maxIdx] != maxIdx) {
            res.add(nums[prev[maxIdx]]);
            maxIdx = prev[maxIdx];
        }
        
        Collections.reverse(res);
        return res;
    }
}