// O(n)time O(1)space
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int n = prices.length;
        
        int hold = -prices[0]; // the max profit when some stock is held in hand
        int sell = 0;  // the max profit when the stock is sold
        int prevSell = 0;  // the max profit when the last stock is sold
        
        for (int i = 1; i < n; i++) {
            hold = Math.max(hold, prevSell - prices[i]);
            prevSell = sell;
            sell = Math.max(sell, hold + prices[i]);
        }
        
        return sell;
    }
}