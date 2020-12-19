class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int n = prices.length;

        int[] canBuy = new int[n];
        int[] canSell = new int[n];
        canBuy[0] = 0;
        canSell[0] = -prices[0] - fee;
        for (int i = 1; i < n; i++) {
            canBuy[i] = Math.max(canBuy[i - 1], canSell[i - 1] + prices[i]);
            canSell[i] = Math.max(canSell[i - 1], canBuy[i - 1] - prices[i] - fee);
        }

        return canBuy[n - 1];
    }
}