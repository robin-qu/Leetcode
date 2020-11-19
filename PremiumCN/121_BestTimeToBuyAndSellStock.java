class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        int min = prices[0];
        int res = 0;
        for (int i = 1; i < n; i++) {
            res = Math.max(res, prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        return res;
    }
}