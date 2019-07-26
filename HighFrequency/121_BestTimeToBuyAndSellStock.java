// O(n)time O(1)space
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int n = prices.length;
        
        int min = prices[0];
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        
        return max;
    }
}