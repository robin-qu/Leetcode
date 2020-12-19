class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int n = prices.length;

        int[] canBuy = new int[n];
        int[] canSell = new int[n];
        int[] coolDown = new int[n];
        canBuy[0] = 0;
        canSell[0] = -prices[0];
        coolDown[0] = Integer.MIN_VALUE;

        for (int i = 1; i < n; i++) {
            canBuy[i] = Math.max(coolDown[i - 1], canBuy[i - 1]);
            canSell[i] = Math.max(canBuy[i - 1] - prices[i], canSell[i - 1]);
            coolDown[i] = canSell[i - 1] + prices[i];
        }

        return Math.max(coolDown[n - 1], canBuy[n - 1]);
    }
}