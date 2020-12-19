class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k == 0) {
            return 0;
        }

        int n = prices.length;

        int[] buys = new int[k];
        int[] sells = new int[k];
        Arrays.fill(buys, Integer.MIN_VALUE);
        Arrays.fill(sells, 0);

        for (int price : prices) {
            buys[0] = Math.max(buys[0], -price);
            sells[0] = Math.max(sells[0], buys[0] + price);
            for (int i = 1; i < k; i++) {
                buys[i] = Math.max(buys[i], sells[i - 1] - price);
                sells[i] = Math.max(sells[i], buys[i] + price);
            }
        }

        return sells[k - 1];
    }
}